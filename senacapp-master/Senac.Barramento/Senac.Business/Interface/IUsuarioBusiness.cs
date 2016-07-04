using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Business.Interface
{
    public interface IUsuarioBusiness
    {
        bool InserirUsuario(Usuario usuario);
        bool AtualizarUsuario(Usuario usuario);
        bool RemoverUsuario(int usuarioId);
        Usuario ProcurarUsuario(string cpf);
        List<Usuario> ListarUsuarios();
    }
}
