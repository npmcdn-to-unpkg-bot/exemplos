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
   public class ComentarioBusiness : IComentarioBusiness
    {

        private IUnitOfWork _uow;

        public ComentarioBusiness(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public void InserirComentario(Comentario comentario)
        {
            _uow.ComentarioRepository.InserirComentario(comentario);
        }

        public void EditarComentario(Comentario comentario)
        {
            _uow.ComentarioRepository.EditarComentario(comentario);
        }

        public async Task<List<Comentario>> ListarComentario()
        {
            return await _uow.ComentarioRepository.ListarComentario();
        }

        public void ExcluirComentario(int comentarioId)
        {
            _uow.ComentarioRepository.ExcluirComentario(comentarioId);
        }

        public async Task<List<Comentario>> ListarComentarioPorPost(int id)
        {
            return await _uow.ComentarioRepository.ListarComentarioPorPost(id);
        }
    }
}
