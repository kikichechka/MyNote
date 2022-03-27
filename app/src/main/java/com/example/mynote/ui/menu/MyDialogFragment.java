package com.example.mynote.ui.menu;

import android.app.Dialog;

import android.os.Bundle;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.publisher.Observer;


public class MyDialogFragment extends DialogFragment {
    private Note note;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(R.string.question_delete)
                .setPositiveButton(R.string.answer_yes, (dialogInterface, i) -> {

                    /*Observer observer = new Observer() {
                        @Override
                        public void receiveMessage(String message, Note note) {
                            ((MainActivity) requireActivity()).getPublisher().unSubscribe(this);
                            ((MainActivity) requireActivity()).getPublisher().sendMessage(Observer.delete, getNote());
                        }
                    };

                    ((MainActivity) requireActivity()).getPublisher().subscribe(observer);*/
                    ((MainActivity) requireActivity()).getPublisher().sendMessage(Observer.delete, getNote());
                    Toast.makeText(requireContext(), R.string.note_delete, Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                })
                .setNeutralButton(R.string.answer_no, null)
                .show();
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
