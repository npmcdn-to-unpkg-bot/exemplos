using Senac.Business.Business;
using Senac.Business.Interface;
using Senac.Data.Interface;
using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace Senac.WebService.Controllers
{
    public class PostController : ApiController
    {
        private PostBusiness _postBusiness;

        public PostController()
        {
            _postBusiness = new PostBusiness();
        }
        
        [HttpPost]
        public void InserirPost(Post post)
        {
            _postBusiness.InserirPost(post);

        }
        [HttpGet]
        public async Task<IList<Post>> ListarPosts()
        {
            return await _postBusiness.ListarPosts();
        }
        [HttpPut]
        public void AtualizarPost(Post post)
        {
            _postBusiness.AtualizarPost(post);
        }
        [HttpDelete]
        public void ExcluirPost(int PostID)
        {
            _postBusiness.ExcluirPost(PostID);
        }

        [HttpGet]
        public Post BuscarPosts(int id)
        {
            return _postBusiness.BuscarPosts(id);
        }
    }
}