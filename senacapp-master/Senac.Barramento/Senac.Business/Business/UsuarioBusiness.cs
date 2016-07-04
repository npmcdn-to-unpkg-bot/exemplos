using Senac.Business.Interface;
using Senac.Data.Interface;
using Senac.Data.Model;
using Senac.Data.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Business.Business
{
    public class UsuarioBusiness : IUsuarioBusiness, IDisposable
    {
        private IUnitOfWork _uow;
        bool disposed = false;

        public UsuarioBusiness(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public bool InserirUsuario(Usuario usuario)
        {
            return _uow.UsuarioRepository.InserirUsuario(usuario);
        }
        public bool AtualizarUsuario(Usuario usuario)
        {
            return _uow.UsuarioRepository.AtualizarUsuario(usuario);
        }

        public bool RemoverUsuario(int usuarioId)
        {
            return _uow.UsuarioRepository.RemoverUsuario(usuarioId);
        }

        public Usuario ProcurarUsuario(string cpf)
        {
            return _uow.UsuarioRepository.ProcurarUsuario(cpf);
        }

        public void Dispose()
        {
            GC.SuppressFinalize(this);
        }

        public List<Usuario> ListarUsuarios()
        {
            return _uow.UsuarioRepository.ListarUsuarios();
        }
    }
}
