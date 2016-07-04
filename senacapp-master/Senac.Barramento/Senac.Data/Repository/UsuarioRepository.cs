using Senac.Data.Interface;
using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Repository
{
    public class UsuarioRepository : IUsuarioRepository
    {
        private SenacDB _db;

        public UsuarioRepository()
        {
            _db = new SenacDB();
        }

        public bool InserirUsuario(Usuario usuario)
        {
            _db.Usuarios.Add(usuario);

            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }
        public bool AtualizarUsuario(Usuario usuario)
        {
            var usuarioEncontrado = _db.Usuarios.Where(u => u.UsuarioID == usuario.UsuarioID).FirstOrDefault();

            if (usuarioEncontrado != null)
            {
                HashAlgorithm hashAlgorithm = new SHA256CryptoServiceProvider();

                byte[] byteValue = Encoding.UTF8.GetBytes(usuario.Senha);

                byte[] byteHash = hashAlgorithm.ComputeHash(byteValue);

                usuarioEncontrado.Nome = usuario.Nome;
                usuarioEncontrado.Cpf = usuario.Cpf;
                usuarioEncontrado.Senha = Convert.ToBase64String(byteHash);
            }
            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;
        }

        public bool RemoverUsuario(int usuarioId)
        {
            var usuarioEncontrado = _db.Usuarios.Where(u => u.UsuarioID == usuarioId).FirstOrDefault();

            if (usuarioEncontrado != null)
            {
                _db.Usuarios.Remove(usuarioEncontrado);
            }

            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }

        public Usuario ProcurarUsuario(string cpf)
        {
            return _db.Usuarios.Where(u => u.Cpf.Equals(cpf)).FirstOrDefault();            
        }

        public List<Usuario> ListarUsuarios()
        {
            return _db.Usuarios.ToList();
        }
    }
}
