using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Interface
{
    public interface IComentarioRepository
    {
        bool InserirComentario(Comentario comentario);
        bool EditarComentario(Comentario comentario);
        Task<List<Comentario>> ListarComentario();
        Task<List<Comentario>> ListarComentarioPorPost(int id);
        bool ExcluirComentario(int comentarioId);
    }
}
