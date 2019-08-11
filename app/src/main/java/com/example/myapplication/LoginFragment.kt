package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myapplication.Entity.Commande
import com.example.myapplication.Entity.Pharmacie
import com.example.myapplication.Entity.User
import com.example.myapplication.Server.RetrofitService
import kotlinx.android.synthetic.main.login_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener{

            getPharmacie(1)
        }

    }

    private fun loadWrapContent(url : String){

        Glide
            .with(this)
            .load(url)
            .into(image1)

    }

    private fun loadmatchCons(url : String){

        Glide
            .with(this)
            .load(url)
            .into(image2)


    }

    private fun loadFixed(url : String){

        Glide
            .with(this)
            .load(url)
            .into(image3)

    }



    // TODO : adapt it to its fragment when copied
    private fun getCommandeParPharmacie(idPharmacie : Int){

        val call = RetrofitService.endpoint.getCommandesParPharmacie(idPharmacie)
        call.enqueue(object : Callback<List<Commande>> {
            override fun onResponse(call: Call<List<Commande>>?, response: Response<List<Commande>>?) {

                if(response?.isSuccessful!!) {

                    Log.d("Nice",response.body().toString())
                    val list : List<Commande>? = response.body()

                    if (list!!.size >= 1) {

        /*                nom1.text = list.get(0)?.id.toString()
                        ville1.text = list.get(0)?.dateEmission
                        if (list.size >= 2 ) {
                            nom2.text = list.get(1)?.id.toString()
                            ville2.text = list.get(1)?.dateEmission
                        }
                        else{
                            nom2.text = "Only one"
                            ville2.text = "Only one"
                        }
          */          }

                } else {
                    Log.d("OnResponse_NOTSuccss",response.toString())
                    Toast.makeText(this@LoginFragment.activity, "From OnResponse NOT Successful", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Commande>>?, t: Throwable?) {
                Log.d("OnFailure", t.toString())
                Toast.makeText(this@LoginFragment.activity, "From OnFailure", Toast.LENGTH_SHORT).show()
            }})
    }

    // TODO : adapt it to its fragment when copied
    private fun updateCommande(commande: Commande){

        val call = RetrofitService.endpoint.updateCommande(commande)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response:
            Response<String>?) {

                if(response?.isSuccessful!!) {

                    Toast.makeText(this@LoginFragment.activity,response.body()+"From isSuccessful", Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this@LoginFragment.activity,"From Not Succesful", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("OnFailure",t.toString())
                Toast.makeText(this@LoginFragment.activity, "From onFailure", Toast.LENGTH_LONG).show()
            }})
    }

    // TODO : adapt it to its fragment when copied
    private fun deleteCommande(commande: Commande){

        val call = RetrofitService.endpoint.deleteCommande(commande)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response:
            Response<String>?) {

                if(response?.isSuccessful!!) {

                    Toast.makeText(this@LoginFragment.activity,response.body()+"From isSuccessful", Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this@LoginFragment.activity,"From Not Succesful", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("OnFailure",t.toString())
                Toast.makeText(this@LoginFragment.activity, "From onFailure", Toast.LENGTH_LONG).show()
            }})
    }


    // TODO : adapt it to its fragment when copied
    private fun addCommande(commande: Commande){

        val call = RetrofitService.endpoint.addCommande(commande)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response:
            Response<String>?) {

                if(response?.isSuccessful!!) {

                    Toast.makeText(this@LoginFragment.activity,response.body()+"From isSuccessful", Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this@LoginFragment.activity,"From Not Succesful", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("OnFailure",t.toString())
                Toast.makeText(this@LoginFragment.activity, "From onFailure", Toast.LENGTH_LONG).show()
            }})
    }


    // TODO : adapt it to its fragment when copied
    private fun getPharmacie(id: Int){

        val call = RetrofitService.endpoint.getPharmacie(id)
        call.enqueue(object : Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {

                if(response?.isSuccessful!!) {

                    Toast.makeText(this@LoginFragment.activity, "From OnResponse Successful", Toast.LENGTH_SHORT).show()
                    val list : List<Pharmacie>? = response.body()

                    if (list != null) {
                        if ( ! list.isEmpty()){

                            var url = list.get(0).photo

                            Log.d("Adresse",url)
                            loadWrapContent(url)
                            loadmatchCons(url)
                            loadFixed(url)
                        }
                    }

                } else {
                    Toast.makeText(this@LoginFragment.activity, "From OnResponse NOT Successful", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                Toast.makeText(this@LoginFragment.activity, "From OnFailure", Toast.LENGTH_SHORT).show()
            }})
    }

    // TODO : adapt it to its fragment when copied
    private fun getPharmaciesParVille( ville: String){

    val call = RetrofitService.endpoint.getPharmaciesParVille(ville)
    call.enqueue(object : Callback<List<Pharmacie>> {
        override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {

            if(response?.isSuccessful!!) {

                val list : List<Pharmacie>? = response.body()

                if (list != null) {
                    if (list.size == 1){
//                        nom1.text = list.get(0)?.nom
//                        ville1.text = list.get(0)?.ville
                    }
                }

            } else {
                Toast.makeText(this@LoginFragment.activity, "From OnResponse NOT Successful", Toast.LENGTH_SHORT).show()
            }
        }
        override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
            Toast.makeText(this@LoginFragment.activity, "From OnFailure", Toast.LENGTH_SHORT).show()
        }})
    }

    // TODO : adapt it to its fragment when copied
    private fun getAllPharmacies() {

        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object : Callback<List<Pharmacie>>{
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {

                if(response?.isSuccessful!!) {

                    if( response != null){
                        Log.d("OnResponse",response.body().toString())
                    }
             /*       val pharmacies: List<Pharmacie>? = response.body()
                    nom1.text = pharmacies?.get(0)?.nom
                    nom2.text = pharmacies?.get(1)?.nom
                    ville1.text = pharmacies?.get(0)?.ville
                    ville2.text = pharmacies?.get(1)?.ville
               */ }
                else {

                    Toast.makeText(this@LoginFragment.activity, "Error !", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                Log.d("OnFailure",t.toString())
                Toast.makeText(this@LoginFragment.activity, "Failed !", Toast.LENGTH_SHORT).show()
            }})


    }

    // TODO : adapt it to its fragment when copied
    private fun addUser(user : User){

        val call = RetrofitService.endpoint.addUser(user)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>?, response:
            Response<String>?) {

                if(response?.isSuccessful!!) {

                    Toast.makeText(this@LoginFragment.activity,response.body()+"From isSuccessful", Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this@LoginFragment.activity,"From else isSuccesful", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("OnFailure",t.toString())
                Toast.makeText(this@LoginFragment.activity, "onFailure", Toast.LENGTH_LONG).show()
            }})
    }
}
