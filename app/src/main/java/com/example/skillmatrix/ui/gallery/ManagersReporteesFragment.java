package com.example.skillmatrix.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillmatrix.R;
import com.example.skillmatrix.databinding.FragmentGalleryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//public class ManagersReporteesFragment extends Fragment {
public class ManagersReporteesFragment extends Fragment {
    protected static final String ACTIVITY_NAME = "ManagersReporteesFragment";
    public static String rootPath = "EmployeeSkillBase";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<String> empList = new ArrayList<String>();
    private FragmentGalleryBinding binding;
// ...

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // setHasOptionsMenu(true);
        ManagersReporteesViewModel galleryViewModel =
                new ViewModelProvider(this).get(ManagersReporteesViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        // Toast.makeText(getContext(),db.toString(), Toast.LENGTH_LONG).show();
        db.collection(rootPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int size = 0; //db.collection(document.getId()).get().getResult().size();
                                empList.add(document.getId());
                                String empID = document.getId();
                                //   Toast.makeText(getContext(),db.toString()+"->"+document.getId()+ " >> " + size + " => " +  db.collection(rootPath).document().collection(document.getId().toString()), Toast.LENGTH_LONG).show();
                                Log.i("TAG##########", document.getId() + " >> " + size + " => " + db.collection(rootPath).document().getPath());
                                ///////////////////
                                //  db.collection(rootPath).document().collection(empID).get().getResult();

                                ////////////////////////////

                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }

                        populateReporteeList();


                    }


                });

     /*   Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

*/
        return root;
    }

    public void populateReporteeList() {
        MyListData[] myListData = new MyListData[empList.size()];
        int i = 0;
        for (String emp : empList) {

            myListData[i] = new MyListData(emp, R.drawable.icons8_reportee);
            i++;
        }
        // Toast.makeText(getContext(), myListData.length, Toast.LENGTH_LONG).show();
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}