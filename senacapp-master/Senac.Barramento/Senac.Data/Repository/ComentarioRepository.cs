using Senac.Data.Interface;
using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Repository
{
   public class ComentarioRepository : IComentarioRepository
    {

        private SenacDB _db;

        public ComentarioRepository()
        {
            _db = new SenacDB();
        }


        public bool InserirComentario(Comentario comentario)
        {
            _db.Comentarios.Add(comentario);

            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }

        public bool EditarComentario(Comentario comentario)
        {
            var comentEncontrado = _db.Comentarios.Where(c => c.ComentarioId == comentario.ComentarioId).FirstOrDefault();

            if (comentEncontrado != null)
            {
                comentEncontrado.Texto = comentario.Texto;
            }
            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }

        public async Task<List<Comentario>> ListarComentario()
        {
            return await _db.Comentarios.ToListAsync();
        }

        public bool ExcluirComentario(int comentarioId)
        {
            var comentEncontrado = _db.Comentarios.Where(c => c.ComentarioId == comentarioId).FirstOrDefault();

            if (comentEncontrado != null)
            {
                _db.Comentarios.Remove(comentEncontrado);
            }

            if (_db.SaveChanges() > 0)
            {
                return true;
            }
            return false;

        }

        public async Task<List<Comentario>> ListarComentarioPorPost(int id)
        {
            return await _db.Comentarios.Where(p => p.Post.PostID == id).ToListAsync();
        }
    }
}
