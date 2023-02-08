package com.seiki.android.docholder.screens.work.documents.select_new_doc_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.FragmentSelectNewDocBinding

class SelectNewDocFragment : Fragment() {
    private lateinit var bind: FragmentSelectNewDocBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentSelectNewDocBinding.inflate(inflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationStart()
        selectDocument()

        bind.imgBtnCircle.setOnClickListener {
            APP.navController.navigate(R.id.action_selectNewDocFragment_to_documentFragment)
        }


    }

    private fun setDoc(value: Int) {
        //шаблон ИД документа
        // тайп: 11 = новый, 12 = изменить 13 = открыть
        val bundle = bundleOf("id" to value,"type" to 11)
        APP.navController.navigate(R.id.action_selectNewDocFragment_to_documentNewFragment, bundle)
    }

    private fun selectDocument() {
        //выбор документа
        //main
        bind.linPasport.setOnClickListener {setDoc(1)}
        bind.linBirthCertificate.setOnClickListener {setDoc(2)}
        bind.linSnils.setOnClickListener {setDoc(3)}
        bind.linMilitaryTicket.setOnClickListener {setDoc(4)}
        bind.linInternationalPassport.setOnClickListener {setDoc(5)}
        bind.linVisa.setOnClickListener {setDoc(6)}
        bind.linDiplomaticPassport.setOnClickListener {setDoc(7)}
        bind.linCertificateServiceman.setOnClickListener {setDoc(8)}
        bind.linSeamanCertificate.setOnClickListener {setDoc(9)}
        bind.linServicePassport.setOnClickListener {setDoc(10)}
        //transport
        bind.linDriverLic.setOnClickListener {setDoc(11)}
        bind.linSTS.setOnClickListener {setDoc(12)}
        bind.linPTS.setOnClickListener {setDoc(13)}
        bind.linOSAGO.setOnClickListener {setDoc(14)}
        bind.linKASKO.setOnClickListener {setDoc(15)}
        //taxes
        bind.linINN.setOnClickListener {setDoc(16)}
        bind.linINNIP.setOnClickListener {setDoc(17)}
        bind.linOGRNIP.setOnClickListener {setDoc(18)}
        bind.linOGRN.setOnClickListener {setDoc(19)}
        bind.linSwiftBankingDetails.setOnClickListener {setDoc(20)}
        bind.linCompanyCard.setOnClickListener {setDoc(21)}
        bind.linBankingDetails.setOnClickListener { setDoc(22)}
        //education
        bind.linGeneralEducation.setOnClickListener {setDoc(23)}
        bind.linHigherEducation.setOnClickListener {setDoc(24)}
        //medic
        bind.linMedicalCard.setOnClickListener {setDoc(25)}
        bind.linOMS.setOnClickListener {setDoc(26)}
        bind.linOMSNew.setOnClickListener {setDoc(27)}
        bind.linVZR.setOnClickListener {setDoc(28)}
        bind.linDMS.setOnClickListener { setDoc(29)}
        //marriage
        bind.linMarriage.setOnClickListener {setDoc(30)}
        bind.linDivorce.setOnClickListener {setDoc(31)}
        //hunting
        bind.linOOOP.setOnClickListener {setDoc(32)}
        bind.linHuntingTicket.setOnClickListener {setDoc(33)}
        bind.linROH.setOnClickListener {setDoc(34)}
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
}