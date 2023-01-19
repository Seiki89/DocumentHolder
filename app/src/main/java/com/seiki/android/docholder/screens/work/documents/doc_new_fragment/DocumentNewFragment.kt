package com.seiki.android.docholder.screens.work.documents.doc_new_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class DocumentNewFragment : Fragment() {
    private lateinit var bind: FragmentDocumentNewBinding
    lateinit var currentDoc: DocModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        bind = FragmentDocumentNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var idLD = arguments?.getInt("id")  //тип докумнта
        var actionType =
            arguments?.getInt("type")  //тип действия с документом(открыть/изменит/новый)
        if (actionType != 11) {
            currentDoc = arguments?.getSerializableCompat("Doc", DocModel::class.java) as DocModel
            actionType = arguments?.getInt("type")
            idLD = currentDoc.idLD
        } else {
            currentDoc = DocModel(
                0, "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", 0, 0, 0)
        }


        when (idLD) {
            //отображение полей для документа
            //main

            1 -> Passport().new(requireActivity(), bind, view, actionType, currentDoc)
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

        bind.imgBtnCircle.setOnClickListener {
            APP.navController.navigate(R.id.action_documentNewFragment_to_documentFragment)
        }


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

}