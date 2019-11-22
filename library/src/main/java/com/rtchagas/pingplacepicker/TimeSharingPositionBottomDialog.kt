package com.rtchagas.pingplacepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rtchagas.pingplacepicker.ui.onclick
import kotlinx.android.synthetic.main.bottom_dialog_fragment.*

class TimeSharingPositionBottomDialog(private val clickListener: OnConfirmButtonClick) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.bottom_dialog_fragment, container, false)

        val btnConfirm = view.findViewById<FloatingActionButton>(R.id.btnConfirm)
        btnConfirm.onclick {
            clickListener.onConfirm(sbTimeSharing.progress)
        }

        val txtTimeSharing = view.findViewById<TextView>(R.id.txtTimeSharing)
        val sbTimeSharing = view.findViewById<SeekBar>(R.id.sbTimeSharing)
        sbTimeSharing.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, b: Boolean) {
                val stepSize = 10
                val currentProgress = (progress/stepSize)*stepSize
                seekBar?.progress = currentProgress
                txtTimeSharing.text = "Minuti : $currentProgress"
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        })

        return view
    }

    interface OnConfirmButtonClick {
        fun onConfirm(timeSharing: Int)
    }
}