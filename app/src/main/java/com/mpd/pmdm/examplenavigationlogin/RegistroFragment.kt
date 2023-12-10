package com.mpd.pmdm.examplenavigationlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mpd.pmdm.examplenavigationlogin.databinding.FragmentRegistroBinding


class RegistroFragment : Fragment() {
    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Devuelve true si el usuario ya está en el map de usuarios
    private fun existeUsuario(): Boolean {
        return LoginFragment.userPasswords.containsKey(binding.editUsuario.text.toString().trim())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Pasamos el foco al edit usuario
        binding.editUsuario.requestFocus()

        binding.btnRegistroSend.setOnClickListener{
            if(camposConValor()){
                if(!existeUsuario()){
                    registroOk()
                } else{
                    mensajeError("El usuario ${binding.editUsuario.text.toString().trim()} ya existe")
                }
            } else {
                mensajeError(getString(R.string.error_usuario_password))
            }
        }

        binding.btnRegistroVolver.setOnClickListener {
            findNavController().popBackStack();
        }
    }

    /**
     * Guarda el nuevo usuario/password en el map, y vuelve a la ventana anterior
     */
    private fun registroOk() {
        LoginFragment.userPasswords.put(
            binding.editUsuario.text.toString().trim(),
            binding.editPassword.text.toString().trim()
        )
        //Mostramos un toast para confirmar el guardado de datos
        Toast.makeText(context, "Registro realizado correctamente", Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }

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
        binding.tvErroresRegistro.text = mensaje
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}