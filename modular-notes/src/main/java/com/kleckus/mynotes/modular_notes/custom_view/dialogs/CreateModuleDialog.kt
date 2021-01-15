package com.kleckus.mynotes.modular_notes.custom_view.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kleckus.mynotes.modular_notes.R
import com.kleckus.mynotes.modular_notes.custom_view.dialogs.CreateModuleDialog.CreationType.CheckList
import com.kleckus.mynotes.modular_notes.custom_view.dialogs.CreateModuleDialog.CreationType.TextView
import kotlinx.android.synthetic.main.create_module_dialog.view.*

object CreateModuleDialog {
    fun openDialog(
        context: Context,
        create : (type : CreationType, title : String) -> Unit
    ){
        val view = getView(context)
        val dialog = MaterialAlertDialogBuilder(context)
            .setView(view)
            .show()
        view.setupView(create, dialog)
    }

    private fun View.setupView(
        create : (type : CreationType, title : String) -> Unit,
        dialog: AlertDialog
    ) {
        checklistChip.setOnClickListener { if(textChip.isChecked) textChip.isChecked = false }
        textChip.setOnClickListener { if(checklistChip.isChecked) checklistChip.isChecked = false }
        addButton.setOnClickListener {
            when{
                textChip.isChecked -> create(TextView, titleField.text.toString())
                checklistChip.isChecked -> create(CheckList, titleField.text.toString())
            }
            dialog.dismiss()
        }
    }

    private fun getView(context : Context)
            = LayoutInflater.from(context).inflate(R.layout.create_module_dialog, null)

    sealed class CreationType{
        object TextView : CreationType()
        object CheckList : CreationType()
    }

}