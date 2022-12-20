package com.example.documentholder.document

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.documentholder.DataBase.Maindb
import com.example.documentholder.document.docs.*
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentDocumentNewBinding
import com.example.documentholder.document.education.GeneralEducation
import com.example.documentholder.document.education.HigherEducation
import com.example.documentholder.document.hunting.HuntingTicket
import com.example.documentholder.document.hunting.LicenseOOOP
import com.example.documentholder.document.hunting.ROH
import com.example.documentholder.document.marriage.CertificateOfDivorce
import com.example.documentholder.document.marriage.MarriageСertificate
import com.example.documentholder.document.medicine.*
import com.example.documentholder.document.taxes.*
import com.example.documentholder.document.transport.*
import com.example.documentholder.misc.DataModel
import com.example.documentholder.misc.Log


class DocumentNewFragment : Fragment() {
    lateinit var bind: FragmentDocumentNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        bind = FragmentDocumentNewBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Maindb.db(requireActivity())
        val dataModel: DataModel by requireActivity().viewModels()
        val actionType = arguments?.getString("act")
        Log("$actionType")

        animationStart()

        bind.imgBtnCircle.setOnClickListener {
            (activity as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, DocumentFragment.newInstance())
                .commit()
        }

        var idLD = 0
        dataModel.msgID.observe(activity as LifecycleOwner) { idLD = it.toString().toInt() }
        when (idLD){
            //main
            1 -> Passport().newPassport(requireActivity(),bind,view,db,actionType)
            2 -> BirthCertificate().newBirthCertificate(requireActivity(),bind,view, db,actionType)
            3 -> SNILS().newSnils(requireActivity(),bind,view, db,actionType)
            4 -> MilitaryTicket().newMilitaryTicket(requireActivity(),bind,view, db,actionType)
            5 -> InternationalPassport().newInternationalPassport(requireActivity(),bind,view, db,actionType)
            6 -> Visa().newVisa(requireActivity(),bind,view, db,actionType)
            7 -> DiplomaticPassport().newDiplomaticPassport(requireActivity(),bind,view, db,actionType)
            8 -> CertificateServiceman().newCertificateServiceman(requireActivity(),bind,view, db,actionType)
            9 -> SeamanCertificate().newSeamanCertificate(requireActivity(),bind,view, db,actionType)
            10 -> ServicePassport().newServicePassport(requireActivity(),bind,view, db,actionType)
            //transport
            11 -> DriverLicense().new(requireActivity(),bind,view, db, actionType)
            12 -> STS().new(requireActivity(),bind,view, db, actionType)
            13 -> PTS().new(requireActivity(),bind,view, db, actionType)
            14 -> OSAGO().new(requireActivity(),bind,view, db, actionType)
            15 -> KASKO().new(requireActivity(),bind,view, db, actionType)
            //taxes
            16 -> INN().new(requireActivity(),bind,view, db, actionType)
            17 -> INNIP().new(requireActivity(),bind,view, db, actionType)
            18 -> OGRNIP().new(requireActivity(),bind,view, db, actionType)
            19 -> OGRN().new(requireActivity(),bind,view, db, actionType)
            20 -> SwiftBankingDetails().new(requireActivity(),bind,view, db, actionType)
            21 -> CompanyСard().new(requireActivity(),bind,view, db, actionType)
            22 -> BankingDetails().new(requireActivity(),bind,view, db, actionType)
            //education
            23 -> GeneralEducation().new(requireActivity(),bind,view, db, actionType)
            24 -> HigherEducation().new(requireActivity(),bind,view, db, actionType)
            //medic
            25 -> MedicalСard().new(requireActivity(),bind,view, db, actionType)
            26 -> OMS().new(requireActivity(),bind,view, db, actionType)
            27 -> OMSNew().new(requireActivity(),bind,view, db, actionType)
            28 -> VZR().new(requireActivity(),bind,view, db, actionType)
            29 -> DMS().new(requireActivity(),bind,view, db, actionType)
            //marriage
            30 -> MarriageСertificate().new(requireActivity(),bind,view, db,actionType)
            31 -> CertificateOfDivorce().new(requireActivity(),bind,view, db,actionType)
            //hunting
            32 -> LicenseOOOP().new(requireActivity(),bind,view, db,actionType)
            33 -> HuntingTicket().new(requireActivity(),bind,view, db,actionType)
            34 -> ROH().new(requireActivity(),bind,view, db,actionType)
        }


    }

    private fun animationStart() {
        bind.imgSaveBtn.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.imgSaveIco.animate().apply {
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

    companion object {
        @JvmStatic
        //fun newInstance() = DocumentNewFragment()
        fun newInstance(act: String): DocumentNewFragment {
            val fragment = DocumentNewFragment()
            val args = Bundle()
            args.putString("act", act)
            fragment.setArguments(args)
            return fragment
        }

    }
}