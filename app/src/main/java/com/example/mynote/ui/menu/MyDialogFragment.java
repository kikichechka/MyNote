package com.example.mynote.ui.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mynote.ui.item_note.NoteFragment;

public class MyDialogFragment extends DialogFragment {
    NoteFragment noteFragment;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("AlertDialog")
                .setMessage("удалить заметку?")
                .setPositiveButton("да", (dialogInterface, i) -> {
                    Toast.makeText(requireContext(),"заметка удалена", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                })
                .setNeutralButton("нет", null)
                .show();
    }
}
