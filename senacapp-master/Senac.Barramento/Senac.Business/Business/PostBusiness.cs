using Senac.Business.Interface;
using Senac.Data;
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
    public class PostBusiness : IPostBusiness
    {
        private PostRepository postRepo;
        public PostBusiness()
        {
            postRepo = new PostRepository();
        }
        // private IUnitOfWork _uow;

        /* public PostBusiness(IUnitOfWork uow)
         {
             _uow = uow;
         }
         public void InserirPost(Post post)
         {
             _uow.PostRespository.InserirPost(post);

         }

         public void AtualizarPost(Post post)
         {
             _uow.PostRespository.AtualizarPost(post);

         }

         public void ExcluirPost(int PostID)
         {
             _uow.PostRespository.ExcluirPost(PostID);

         }
         public IList<Post> ListarPosts()
         {

             return _uow.PostRespository.ListarPosts();
         }*/
        public void InserirPost(Post post)
        {
            postRepo.InserirPost(post);
        }

        public void AtualizarPost(Post post)
        {
            postRepo.AtualizarPost(post);
        }

        public void ExcluirPost(int PostID)
        {
            postRepo.ExcluirPost(PostID);
        }
        public async Task<IList<Post>> ListarPosts()
        {
            return await postRepo.ListarPosts();
        }

        public Post BuscarPosts(int postId)
        {
            return postRepo.BuscarPosts(postId);
        }
    }
}
