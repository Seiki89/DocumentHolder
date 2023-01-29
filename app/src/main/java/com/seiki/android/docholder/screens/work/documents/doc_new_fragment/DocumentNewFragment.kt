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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentDocumentNewBinding
import com.seiki.android.docholder.getSerializableCompat
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.screens.work.documents.PhotoViewModel
import com.seiki.android.docholder.screens.work.documents.listDocuments.education.GeneralEducation
import com.seiki.android.docholder.screens.work.documents.listDocuments.education.HigherEducation
import com.seiki.android.docholder.screens.work.documents.listDocuments.hunting.HuntingTicket
import com.seiki.android.docholder.screens.work.documents.listDocuments.hunting.LicenseOOOP
import com.seiki.android.docholder.screens.work.documents.listDocuments.hunting.ROH
import com.seiki.android.docholder.screens.work.documents.listDocuments.main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    private val photoData: PhotoViewModel by activityViewModels()

    private var startValue1 = ""
    private var startValue2 = ""
    private var startValue3 = ""
    private var startValue4 = ""

    private var numPhotoButton: Int = 0
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var outputDir: File
    private var imageCapture: ImageCapture? = null

    private fun getOutputDir(): File { return requireActivity().filesDir }

    private fun allPermissionGranted() = PERMISSION.all {
        //проверка пермишонов
        ContextCompat.checkSelfPermission(requireActivity(),
            it) == PackageManager.PERMISSION_GRANTED
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

        photoDataIsZero()
        outputDir = getOutputDir()
        cameraExecutor = Executors.newSingleThreadExecutor()

        var idLD = arguments?.getInt("id")
        var actionType = arguments?.getInt("type")
        if (actionType != 11) {
            currentDoc = arguments?.getSerializableCompat("Doc", DocModel::class.java) as DocModel
            actionType = arguments?.getInt("type")
            idLD = currentDoc.idLD
        } else {
            currentDoc = ForDoc().zeroBaseInit
        }

        startValue()
        animateStart()
        changeDoc(idLD, view, actionType)
        enabledPic(actionType)
        photoOrSee(actionType)
        buttonsInit(actionType)
        delPhoto()

    }

    private fun delPhoto() {
        var val1 = ""
        var val2 = ""
        var val3 = ""
        var val4 = ""
        photoData.messagePhoto1.observe(activity as LifecycleOwner) { val1 = it }
        photoData.messagePhoto2.observe(activity as LifecycleOwner) { val2 = it }
        photoData.messagePhoto3.observe(activity as LifecycleOwner) { val3 = it }
        photoData.messagePhoto4.observe(activity as LifecycleOwner) { val4 = it }

        bind.photo1.setOnLongClickListener {
            bind.txtDelPhoto1.visibility = View.VISIBLE
            true
        }
        bind.photo2.setOnLongClickListener {
            bind.txtDelPhoto2.visibility = View.VISIBLE
            true
        }
        bind.photo3.setOnLongClickListener {
            bind.txtDelPhoto3.visibility = View.VISIBLE
            true
        }
        bind.photo4.setOnLongClickListener {
            bind.txtDelPhoto4.visibility = View.VISIBLE
            true
        }
        bind.txtDelPhoto1.apply {
            setOnLongClickListener {
                stringToFileWithDelete(val1)
                photoData.messagePhoto1.value = ""
                bind.photo1doc.setImageResource(R.drawable.ic_baseline_photo_camera_24)
                bind.txtDelPhoto1.visibility = View.GONE
                true
            }
            setOnClickListener{
                bind.txtDelPhoto1.visibility = View.GONE
            }
        }

        bind.txtDelPhoto2.apply {
            setOnLongClickListener {
                stringToFileWithDelete(val2)
                photoData.messagePhoto2.value = ""
                bind.photo2doc.setImageResource(R.drawable.ic_baseline_photo_camera_24)
                bind.txtDelPhoto2.visibility = View.GONE
                true
            }
            setOnClickListener{
                bind.txtDelPhoto2.visibility = View.GONE
            }
        }

        bind.txtDelPhoto3.apply {
            setOnLongClickListener {
                stringToFileWithDelete(val3)
                photoData.messagePhoto3.value = ""
                bind.photo3doc.setImageResource(R.drawable.ic_baseline_photo_camera_24)
                bind.txtDelPhoto3.visibility = View.GONE
                true
            }
            setOnClickListener{
                bind.txtDelPhoto3.visibility = View.GONE
            }
        }

        bind.txtDelPhoto4.apply {
            setOnLongClickListener {
                stringToFileWithDelete(val4)
                photoData.messagePhoto4.value = ""
                bind.photo4doc.setImageResource(R.drawable.ic_baseline_photo_camera_24)
                bind.txtDelPhoto4.visibility = View.GONE
                true
            }
            setOnClickListener{
                bind.txtDelPhoto4.visibility = View.GONE
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun startValue() {
        //пути файлов при открытии
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            var val1= ""
            var val2= ""
            var val3= ""
            var val4= ""
            photoData.messagePhoto1.observe(activity as LifecycleOwner) { val1 = it }
            photoData.messagePhoto2.observe(activity as LifecycleOwner) { val2 = it }
            photoData.messagePhoto3.observe(activity as LifecycleOwner) { val3 = it }
            photoData.messagePhoto4.observe(activity as LifecycleOwner) { val4 = it }
            startValue1 = val1
            startValue2 = val2
            startValue3 = val3
            startValue4 = val4
        }
    }

    private fun photoDataIsZero() {
        //обнулить пути файлов
        photoData.messagePhoto1.value = ""
        photoData.messagePhoto2.value = ""
        photoData.messagePhoto3.value = ""
        photoData.messagePhoto4.value = ""
    }

    private fun stringToFileWithDelete(value: String){
        //удалить файл
        val uri = Uri.parse(value)
        val path = uri.path
        val file = File(path!!)
        file.delete()
    }

    private fun buttonsInit(actionType: Int?) {
        bind.imgBtnCircle.setOnClickListener {
            var endValue1 = ""
            var endValue2 = ""
            var endValue3 = ""
            var endValue4 = ""
            photoData.messagePhoto1.observe(activity as LifecycleOwner) { endValue1 = it }
            photoData.messagePhoto2.observe(activity as LifecycleOwner) { endValue2 = it }
            photoData.messagePhoto3.observe(activity as LifecycleOwner) { endValue3 = it }
            photoData.messagePhoto4.observe(activity as LifecycleOwner) { endValue4 = it }
            if (actionType == 11) {
                stringToFileWithDelete(endValue1)
                stringToFileWithDelete(endValue2)
                stringToFileWithDelete(endValue3)
                stringToFileWithDelete(endValue4)
            }
            if (actionType == 12){
                if (startValue1 != endValue1) {
                    stringToFileWithDelete(endValue1)
                }
                if (startValue2 != endValue2) {
                    stringToFileWithDelete(endValue2)
                }
                if (startValue3 != endValue3) {
                    stringToFileWithDelete(endValue3)
                }
                if (startValue4 != endValue4) {
                    stringToFileWithDelete(endValue4)
                }
            }
            photoDataIsZero()

            APP.navController.navigate(R.id.action_documentNewFragment_to_documentFragment)
        }

        bind.exitViewPhoto.setOnClickListener { bind.photoCardDoc.visibility = View.GONE }

        bind.cancelPhoto.setOnClickListener {
            bind.photoCardDoc.visibility = View.GONE
            cameraExecutor.shutdown()
        }

        bind.savePhoto.setOnClickListener {
            bind.photoCardDoc.visibility = View.GONE

            var val1 = ""
            var val2 = ""
            var val3 = ""
            var val4 = ""
            photoData.messagePhoto1.observe(activity as LifecycleOwner) { val1 = it }
            photoData.messagePhoto2.observe(activity as LifecycleOwner) { val2 = it }
            photoData.messagePhoto3.observe(activity as LifecycleOwner) { val3 = it }
            photoData.messagePhoto4.observe(activity as LifecycleOwner) { val4 = it }

            when (numPhotoButton) {
                1 -> {
                    stringToFileWithDelete(val1)
                    takePhoto("фото №1 сохранено ", bind.photo1doc)
                }
                2 -> {
                    stringToFileWithDelete(val2)
                    takePhoto("фото №2 сохранено ", bind.photo2doc)
                }
                3 -> {
                    stringToFileWithDelete(val3)
                    takePhoto("фото №3 сохранено ", bind.photo3doc)
                }
                4 -> {
                    stringToFileWithDelete(val4)
                    takePhoto("фото №4 сохранено ", bind.photo4doc)
                }
            }
            cameraExecutor.shutdown()
        }
    }

    private fun photoOrSee(actionType: Int?) {
        //просмотр или изменение фото
        bind.photo1.setOnClickListener {
            if (actionType == 13) {
                seePhoto(photoData.messagePhoto1.observe(activity as LifecycleOwner) {
                    bind.photoViewer.setImageURI(Uri.parse(it)) })
            } else  {
                numPhotoButton = 1
                startPhoto()

            }
        }

        bind.photo2.setOnClickListener {
            if (actionType == 13) {
                seePhoto(photoData.messagePhoto2.observe(activity as LifecycleOwner) {
                    bind.photoViewer.setImageURI(Uri.parse(it)) })
            } else  {
                numPhotoButton = 2
                startPhoto()
            }
        }

        bind.photo3.setOnClickListener {
            if (actionType == 13) {
                seePhoto(photoData.messagePhoto3.observe(activity as LifecycleOwner) {
                    bind.photoViewer.setImageURI(Uri.parse(it)) })
            } else  {
                numPhotoButton = 3
                startPhoto()
            }
        }

        bind.photo4.setOnClickListener {
            if (actionType == 13) {
                seePhoto(photoData.messagePhoto4.observe(activity as LifecycleOwner) {
                    bind.photoViewer.setImageURI(Uri.parse(it)) })
            } else  {
                numPhotoButton = 4
                startPhoto()

            }
        }
    }

    private fun enabledPic(actionType: Int?) {
        //при просмотре: отключить изображения которые не сохранены
        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            if (actionType == 13) {
                photoData.messagePhoto1.observe(activity as LifecycleOwner) {
                    if (it.isNullOrBlank()) {
                        bind.photo1.isEnabled = false
                    }
                }
                photoData.messagePhoto2.observe(activity as LifecycleOwner) {
                    if (it.isNullOrBlank()) {
                        bind.photo2.isEnabled = false
                    }
                }
                photoData.messagePhoto3.observe(activity as LifecycleOwner) {
                    if (it.isNullOrBlank()) {
                        bind.photo3.isEnabled = false
                    }
                }
                photoData.messagePhoto4.observe(activity as LifecycleOwner) {
                    if (it.isNullOrBlank()) {
                        bind.photo4.isEnabled = false
                    }
                }
            }
        }
    }

    private fun seePhoto(value: Unit) {
        //режим просмотра файла
        bind.photoCardDoc.visibility = View.VISIBLE
        bind.exitViewPhoto.visibility = View.VISIBLE
        bind.photoViewer.visibility = View.VISIBLE
        bind.cancelPhoto.visibility = View.GONE
        bind.savePhoto.visibility = View.GONE
        bind.photoFinder.visibility = View.GONE
        value
    }

    private fun startPhoto() {
        //инициировать фотосьемку
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
                Toast.makeText(requireActivity(), "Ошибка доступа к камере", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun startCamera() {
        //организация превью камеры
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
                .also {
                    it.setSurfaceProvider(bind.photoFinder.surfaceProvider)
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Log.e(TAG,
                    "Bind_Error", e)
            }
        }, ContextCompat.getMainExecutor(requireContext())
        )
    }

    private fun takePhoto(value: String, image: ImageView) {
        //сделать фото
        val imageCapture = imageCapture ?: return

        val photoFile =
            File(outputDir,
                SimpleDateFormat(FILE_FORMAT,
                    Locale.US)
                    .format(System.currentTimeMillis()) + ".jpg"
            )

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile)
            .build()                //подготовка - помещаем изображение в файл

        imageCapture.takePicture(
            outputOption,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val uri = Uri.fromFile(photoFile)

                    when (numPhotoButton) {
                        1 -> {
                            photoData.messagePhoto1.value = uri.toString()
                        }
                        2 -> {
                            photoData.messagePhoto2.value = uri.toString()
                        }
                        3 -> {
                            photoData.messagePhoto3.value = uri.toString()
                        }
                        4 -> {
                            photoData.messagePhoto4.value = uri.toString()
                        }
                    }

                    image.setImageURI(uri)
                    Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        requireContext(), "Ошибка сохранения: ${exception.message}",
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
            1 -> Passport().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            2 -> BirthCertificate().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            3 -> SNILS().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            4 -> MilitaryTicket().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            5 -> InternationalPassport().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            6 -> Visa().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            7 -> DiplomaticPassport().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            8 -> CertificateServiceman().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            9 -> SeamanCertificate().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            10 -> ServicePassport().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            //transport
            11 -> DriverLicense().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            12 -> STS().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            13 -> PTS().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            14 -> OSAGO().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            15 -> KASKO().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            //taxes
            16 -> INN().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            17 -> INNIP().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            18 -> OGRNIP().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            19 -> OGRN().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            20 -> SwiftBankingDetails().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            21 -> CompanyСard().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            22 -> BankingDetails().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            //education
            23 -> GeneralEducation().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            24 -> HigherEducation().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            //medic
            25 -> MedicalCard().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            26 -> OMS().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            27 -> OMSNew().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            28 -> VZR().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            29 -> DMS().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            //marriage
            30 -> MarriageCertificate().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            31 -> CertificateOfDivorce().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            //hunting
            32 -> LicenseOOOP().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            33 -> HuntingTicket().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
            34 -> ROH().new(requireActivity(), bind, view, actionType, currentDoc, photoData)
        }
    }

    companion object {
        private const val FILE_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val TAG = "MyTag"
        private const val PERMISSION_CODE = 10
        private val PERMISSION = arrayOf(android.Manifest.permission.CAMERA)
    }

}