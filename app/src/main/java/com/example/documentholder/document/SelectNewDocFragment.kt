package com.example.documentholder.document

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.documentholder.R
import com.example.documentholder.databinding.FragmentSelectNewDocBinding
import com.example.documentholder.misc.DataModel

class SelectNewDocFragment : Fragment() {
    lateinit var bind: FragmentSelectNewDocBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentSelectNewDocBinding.inflate(inflater)
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataModel: DataModel by requireActivity().viewModels()
        animationStart()

        //main
        bind.linPasport.setOnClickListener {dataModel.msgID.value = 1;openDocs()}
        bind.linBirthCertificate.setOnClickListener {dataModel.msgID.value = 2;openDocs()}
        bind.linSnils.setOnClickListener {dataModel.msgID.value = 3;openDocs()}
        bind.linMilitaryTicket.setOnClickListener {dataModel.msgID.value = 4;openDocs()}
        bind.linInternationalPassport.setOnClickListener {dataModel.msgID.value = 5;openDocs()}
        bind.linVisa.setOnClickListener {dataModel.msgID.value = 6;openDocs()}
        bind.linDiplomaticPassport.setOnClickListener {dataModel.msgID.value = 7;openDocs()}
        bind.linCertificateServiceman.setOnClickListener {dataModel.msgID.value = 8;openDocs()}
        bind.linSeamanCertificate.setOnClickListener {dataModel.msgID.value = 9;openDocs()}
        bind.linServicePassport.setOnClickListener {dataModel.msgID.value = 10;openDocs()}
        //transport
        bind.linDriverLic.setOnClickListener {dataModel.msgID.value = 11;openDocs()}
        bind.linSTS.setOnClickListener {dataModel.msgID.value = 12;openDocs()}
        bind.linPTS.setOnClickListener {dataModel.msgID.value = 13;openDocs()}
        bind.linOSAGO.setOnClickListener {dataModel.msgID.value = 14;openDocs()}
        bind.linKASKO.setOnClickListener {dataModel.msgID.value = 15;openDocs()}
        //taxes
        bind.linINN.setOnClickListener {dataModel.msgID.value = 16;openDocs()}
        bind.linINNIP.setOnClickListener {dataModel.msgID.value = 17;openDocs()}
        bind.linOGRNIP.setOnClickListener {dataModel.msgID.value = 18;openDocs()}
        bind.linOGRN.setOnClickListener {dataModel.msgID.value = 19;openDocs()}
        bind.linSwiftBankingDetails.setOnClickListener {dataModel.msgID.value = 20;openDocs()}
        bind.linCompanyCard.setOnClickListener {dataModel.msgID.value = 21;openDocs()}
        bind.linBankingDetails.setOnClickListener {dataModel.msgID.value = 22;openDocs()}
        //education
        bind.linGeneralEducation.setOnClickListener {dataModel.msgID.value = 23;openDocs()}
        bind.linHigherEducation.setOnClickListener {dataModel.msgID.value = 24;openDocs()}
        //medic
        bind.linMedicalCard.setOnClickListener {dataModel.msgID.value = 25;openDocs()}
        bind.linOMS.setOnClickListener {dataModel.msgID.value = 26;openDocs()}
        bind.linOMSNew.setOnClickListener {dataModel.msgID.value = 27;openDocs()}
        bind.linVZR.setOnClickListener {dataModel.msgID.value = 28;openDocs()}
        bind.linDMS.setOnClickListener {dataModel.msgID.value = 29;openDocs()}
        //marriage
        bind.linMarriage.setOnClickListener {dataModel.msgID.value = 30;openDocs()}
        bind.linDivorce.setOnClickListener {dataModel.msgID.value = 31;openDocs()}
        //hunting
        bind.linOOOP.setOnClickListener {dataModel.msgID.value = 32;openDocs()}
        bind.linHuntingTicket.setOnClickListener {dataModel.msgID.value = 33;openDocs()}
        bind.linROH.setOnClickListener {dataModel.msgID.value = 34;openDocs()} //TODO: последнее значение idLD (34)


        bind.imgBtnCircle.setOnClickListener {
            (activity as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.docFragmentHolder, DocumentFragment.newInstance())
                .commit()
        }

    }

    private fun openDocs(){
        (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.docFragmentHolder, DocumentNewFragment.newInstance("new"))
            .commit()
    }

    private fun animationStart() {
        bind.imgBtnCircle.animate().apply {
            duration = 400
            rotationYBy(360f)
        }
        bind.imageView3.animate().apply {
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
        fun newInstance() = SelectNewDocFragment()
    }
}