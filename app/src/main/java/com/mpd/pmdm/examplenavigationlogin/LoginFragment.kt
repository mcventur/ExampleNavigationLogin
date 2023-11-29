package com.mpd.pmdm.examplenavigationlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mpd.pmdm.examplenavigationlogin.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Boton login
        binding.btnLoginSend.setOnClickListener {
            if (camposConValor()) {
                val usuarioIntroducido = binding.editUsuario.text.toString()
                val passwordIntroducido = binding.editPassword.text.toString()
                if (userPasswords[usuarioIntroducido] == passwordIntroducido) {
                    entrar(usuarioIntroducido)
                }
                else{
                    mensajeError("Usuario o contraseña incorrectos")
                }
            }
        }

        //
        binding.btnLoginVolver.setOnClickListener {
            //Volvemos al fragmento anterior de la pila
            //https://developer.android.com/guide/navigation/backstack?hl=es-419#pop-back
            findNavController().popBackStack()
        }
    }

    /**
     * Realiza la navegación si el login es correcto
     */
    private fun entrar(usuario: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToCitasFragment(usuario)
        val navOptions: NavOptions
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     * Devuelve true si no faltan valores
     */
    private fun camposConValor(): Boolean {
        if (binding.editUsuario.text.toString().isEmpty() ||
            binding.editPassword.text.toString().isEmpty()
            ) {
            mensajeError("Debes introducir usuario y contraseña")
            return false
        }
        return true
    }

    private fun mensajeError(mensaje: String) {
        binding.tvErroresLogin.text = mensaje
    }

    companion object {
        val userPasswords = mutableMapOf<String, String>("max" to "power", "elena" to "nito_del_bosque")
    }


}