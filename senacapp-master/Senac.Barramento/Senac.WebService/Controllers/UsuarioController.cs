using Senac.Business.Business;
using Senac.Business.Interface;
using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace Senac.WebService.Controllers
{
    public class UsuarioController : ApiController
    {
        private IUsuarioBusiness _usuarioBusiness;

        public UsuarioController(IUsuarioBusiness usuarioBusiness)
        {
            _usuarioBusiness = usuarioBusiness;
        }

        [HttpGet]
        public List<Usuario> ListarUsuarios()
        {
            return _usuarioBusiness.ListarUsuarios();
        }



        [HttpGet]
        public Usuario ProcurarUsuario(string Cpf)
        {
            return _usuarioBusiness.ProcurarUsuario(Cpf);
        }


        [HttpPost]
        public bool InserirUsuario(Usuario usuario)
        {
            return _usuarioBusiness.InserirUsuario(usuario);
        }

        [HttpPut]
        public bool AtualizarUsuario(Usuario usuario)
        {
            return _usuarioBusiness.AtualizarUsuario(usuario);
        }

        [HttpDelete]
        public bool RemoverUsuario(int usuarioId)
        {
            return _usuarioBusiness.RemoverUsuario(usuarioId);
        }
    }
}