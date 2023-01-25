package com.seiki.android.docholder.screens.work.documents.doc_new_fragment

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.getSerializableCompat
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.listDocuments.education.GeneralEducation
import com.seiki.android.docholder.screens.work.documents.listDocuments.education.HigherEducation
import com.seiki.android.docholder.screens.work.documents.listDocuments.hunting.HuntingTicket
import com.seiki.android.docholder.screens.work.documents.listDocuments.hunting.LicenseOOOP
import com.seiki.android.docholder.screens.work.documents.listDocuments.hunting.ROH
import com.seiki.android.docholder.screens.work.documents.listDocuments.main.*
import com.seiki.android.docholder.screens.work.documents.listDocuments.marriage.CertificateOfDivorce
import com.seiki.android.docholder.screens.work.documents.listDocuments.marriage.MarriageCertificate
import com.seiki.android.docholder.screens.work.documents.listDocuments.medicine.*
import com.seiki.android.docholder.screens.work.documents.listDocuments.taxes.*
import com.seiki.android.docholder.screens.work.documents.listDocuments.transport.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DocumentNewFragment : Fragment() {
    private lateinit var bind: FragmentDocumentNewBinding
    lateinit var currentDoc: DocModel


    private var photo1:String = ""
    private var photo2:String = ""
    private var photo3:String = ""
    private var photo4:String = ""
    private var numPhotoButton:Int = 0
    private lateinit var cameraExecutor: ExecutorService                                            //управление потоками
    private lateinit var outputDir : File                                                           //папка для сохранения
    private var imageCapture: ImageCapture? = null                                                  //захваченное изображение
    private fun getOutputDir(): File {return requireActivity().filesDir}                            //если не 0 и существует, то медиадир, если нет то стандарт
    private fun allPermissionGranted() = PERMISSION.all{
        //проверка пермишонов
        ContextCompat.checkSelfPermission(requireActivity(), it) == PackageManager.PERMISSION_GRANTED
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        bind = FragmentDocumentNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        outputDir = getOutputDir()                                                                  //создали папку
        cameraExecutor = Executors.newSingleThreadExecutor()                                        //инициализируем отдельный поток для камеры для обмена между потоками

        var idLD = arguments?.getInt("id")  //тип докумнта
        var actionType =
            arguments?.getInt("type")  //тип действия с документом(открыть/изменит/новый)
        if (actionType != 11) {
            currentDoc = arguments?.getSerializableCompat("Doc", DocModel::class.java) as DocModel
            actionType = arguments?.getInt("type")
            idLD = currentDoc.idLD
        } else {
            currentDoc = ForDoc().zeroBaseInit
        }

        animateStart()
        changeDoc(idLD, view, actionType)

        bind.imgBtnCircle.setOnClickListener {
            APP.navController.navigate(R.id.action_documentNewFragment_to_documentFragment)
        }

        //начать фото
        bind.photo1.setOnClickListener { startPhoto() ; numPhotoButton = 1}
        bind.photo2.setOnClickListener { startPhoto() ; numPhotoButton = 2}
        bind.photo3.setOnClickListener { startPhoto() ; numPhotoButton = 3}
        bind.photo4.setOnClickListener { startPhoto() ; numPhotoButton = 4}

        bind.cancelPhoto.setOnClickListener {
            bind.photoCardDoc.visibility = View.GONE
            cameraExecutor.shutdown()
        }

        bind.savePhoto.setOnClickListener {
            bind.photoCardDoc.visibility = View.GONE
            when (numPhotoButton){
                1->{takePhoto("фото №1 сохранено ",bind.photo1doc)}
                2->{takePhoto("фото №2 сохранено ",bind.photo2doc)}
                3->{takePhoto("фото №3 сохранено ",bind.photo3doc)}
                4->{takePhoto("фото №4 сохранено ",bind.photo4doc)}
            }
            changeDoc(idLD, view, actionType)
            cameraExecutor.shutdown()
        }


    }

    private fun startPhoto() {
        if (allPermissionGranted()) {
            //если пермишны есть - запустить камеру
            bind.photoCardDoc.visibility = View.VISIBLE

            startCamera()
        } else {
            //если пермишонов нет - запросить их
            ActivityCompat.requestPermissions(
                requireActivity(),
                PERMISSION,
                PERMISSION_CODE
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        //после повторного запроса пермишонов (если их небыло)
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (allPermissionGranted()) {
                startCamera()
            } else {
                Toast.makeText(requireActivity(), "Ошибка доступа к камере", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCamera() {
        //организация превью камеры
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())              //привязка к основному потоку лайфцайкла

        cameraProviderFuture.addListener({                                                          //связываем потоки
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
                .also {
                    it.setSurfaceProvider(bind.photoFinder.surfaceProvider)                         //где отображать превью камеры
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA                                 //какой камерой пользоваться

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()                                                          //проверить что сейчас камера не с чем не связанна
                cameraProvider.bindToLifecycle(
                    this,                                                               //привязка к лайфсайкл
                    cameraSelector,                                                                 //какая камера
                    preview,                                                                        //где отображаем
                    imageCapture                                                                    //изображение в жизненный цикл для сохранения
                )
            }catch (e: Exception){
                Log.e(TAG,"Bind_Error",e)                                                      //при ошибке: текст + ошибка
            }
        }
            , ContextCompat.getMainExecutor(requireContext())
        )
    }

    private fun takePhoto(value:String, image: ImageView) {
        val imageCapture = imageCapture ?: return                                                   // ? если ноль то прерываем

        val photoFile = File(outputDir,                                                             //создаем файл, папка файла
            SimpleDateFormat(FILE_FORMAT, Locale.US)                                           //имя файла + формат
                .format(System.currentTimeMillis()) + ".jpg"                                        //сформированное имя
        )

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()                //подготовка - помещаем изображение в файл


        imageCapture.takePicture(
            outputOption,                                                                           //подготовленный файл
            ContextCompat.getMainExecutor(requireContext()),                                        //передача инфы между потоками
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val uri = Uri.fromFile(photoFile)

                    when (numPhotoButton){
                        1->{photo1 = uri.toString()}
                        2->{photo2 = uri.toString()}
                        3->{photo3 = uri.toString()}
                        4->{photo4 = uri.toString()}
                    }

                    image.setImageURI(uri)
                    Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        requireContext(),"Ошибка сохранения: ${exception.message}",
                        Toast.LENGTH_LONG).show()
                }
            }
        )
    }



    private fun animateStart() {
        //стартовая анмация
        bind.imgBtnCircle.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.imgSaveBtn.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.header.animate().apply {
            duration = 400
            rotationX(180f)
        }
        bind.txtHeader.animate().apply {
            duration = 400
            rotationX(180f)
        }
    }

    private fun changeDoc(idLD: Int?, view: View, actionType: Int?) {
        //отображение полей для документа
        when (idLD) {
            //main
            1 -> Passport().new(requireActivity(), bind, view, actionType, currentDoc,photo1,photo2,photo3,photo4)
            2 -> BirthCertificate().new(requireActivity(), bind, view, actionType, currentDoc)
            3 -> SNILS().new(requireActivity(), bind, view, actionType, currentDoc)
            4 -> MilitaryTicket().new(requireActivity(), bind, view, actionType, currentDoc)
            5 -> InternationalPassport().new(requireActivity(), bind, view, actionType, currentDoc)
            6 -> Visa().new(requireActivity(), bind, view, actionType, currentDoc)
            7 -> DiplomaticPassport().new(requireActivity(), bind, view, actionType, currentDoc)
            8 -> CertificateServiceman().new(requireActivity(), bind, view, actionType, currentDoc)
            9 -> SeamanCertificate().new(requireActivity(), bind, view, actionType, currentDoc)
            10 -> ServicePassport().new(requireActivity(), bind, view, actionType, currentDoc)
            //transport
            11 -> DriverLicense().new(requireActivity(), bind, view, actionType, currentDoc)
            12 -> STS().new(requireActivity(), bind, view, actionType, currentDoc)
            13 -> PTS().new(requireActivity(), bind, view, actionType, currentDoc)
            14 -> OSAGO().new(requireActivity(), bind, view, actionType, currentDoc)
            15 -> KASKO().new(requireActivity(), bind, view, actionType, currentDoc)
            //taxes
            16 -> INN().new(requireActivity(), bind, view, actionType, currentDoc)
            17 -> INNIP().new(requireActivity(), bind, view, actionType, currentDoc)
            18 -> OGRNIP().new(requireActivity(), bind, view, actionType, currentDoc)
            19 -> OGRN().new(requireActivity(), bind, view, actionType, currentDoc)
            20 -> SwiftBankingDetails().new(requireActivity(), bind, view, actionType, currentDoc)
            21 -> CompanyСard().new(requireActivity(), bind, view, actionType, currentDoc)
            22 -> BankingDetails().new(requireActivity(), bind, view, actionType, currentDoc)
            //education
            23 -> GeneralEducation().new(requireActivity(), bind, view, actionType, currentDoc)
            24 -> HigherEducation().new(requireActivity(), bind, view, actionType, currentDoc)
            //medic
            25 -> MedicalCard().new(requireActivity(), bind, view, actionType, currentDoc)
            26 -> OMS().new(requireActivity(), bind, view, actionType, currentDoc)
            27 -> OMSNew().new(requireActivity(), bind, view, actionType, currentDoc)
            28 -> VZR().new(requireActivity(), bind, view, actionType, currentDoc)
            29 -> DMS().new(requireActivity(), bind, view, actionType, currentDoc)
            //marriage
            30 -> MarriageCertificate().new(requireActivity(), bind, view, actionType, currentDoc)
            31 -> CertificateOfDivorce().new(requireActivity(), bind, view, actionType, currentDoc)
            //hunting
            32 -> LicenseOOOP().new(requireActivity(), bind, view, actionType, currentDoc)
            33 -> HuntingTicket().new(requireActivity(), bind, view, actionType, currentDoc)
            34 -> ROH().new(requireActivity(), bind, view, actionType, currentDoc)
        }
    }

    companion object{
        private const val FILE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val TAG = "MyTag"
        private const val PERMISSION_CODE = 10
        private val PERMISSION = arrayOf(android.Manifest.permission.CAMERA)
    }

}