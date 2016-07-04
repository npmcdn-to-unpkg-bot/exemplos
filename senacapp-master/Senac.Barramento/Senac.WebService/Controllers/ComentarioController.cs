using Senac.Business.Business;
using Senac.Business.Interface;
using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace Senac.WebService.Controllers
{
    public class ComentarioController : ApiController
    {
        private IComentarioBusiness _comentarioBusiness;

        public ComentarioController(IComentarioBusiness comentarioBusiness)
        {
            _comentarioBusiness = comentarioBusiness;
        }


        [HttpPost]
        public void InserirComentario(Comentario comentario)
        {
            _comentarioBusiness.InserirComentario(comentario);
        }

        [HttpGet]
        public async Task<List<Comentario>> ListarComentario()
        {
            return await _comentarioBusiness.ListarComentario();
        }

        /// <summary>
        /// Realiza a listagem de comentarios por id do post
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public async Task<List<Comentario>> ListarComentarioPorPost(int id)
        {
            return await _comentarioBusiness.ListarComentarioPorPost(id);
        }

        [HttpPut]
        public void EditarComentario(Comentario comentario)
        {
            _comentarioBusiness.EditarComentario(comentario);
        }

        [HttpDelete]
        public void ExcluirComentario(int comentarioId)
        {
            _comentarioBusiness.ExcluirComentario(comentarioId);
        }

    }
}