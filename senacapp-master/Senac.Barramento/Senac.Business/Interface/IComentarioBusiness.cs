using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Business.Interface
{
    public interface IComentarioBusiness
    {
        void InserirComentario(Comentario comentario);
        void EditarComentario(Comentario comentario);
        Task<List<Comentario>> ListarComentario();
        Task<List<Comentario>> ListarComentarioPorPost(int id);
        void ExcluirComentario(int comentarioId);
    }
}
