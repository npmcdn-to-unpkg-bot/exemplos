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
    public class PostRepository : IPostRepository
    {
        private SenacDB db;

        public PostRepository()
        {
            db = new SenacDB();
        }
        public void InserirPost(Post post)
        {
            db.Post.Add(post);
            db.SaveChanges();
        }

        public void AtualizarPost(Post post)
        {
            var pos = db.Post.Where(p => p.PostID == post.PostID).FirstOrDefault();
            if (pos != null)
            {
                pos = post;
                db.SaveChanges();
            }

        }

        public void ExcluirPost(int PostID)
        {
            var post = db.Post.Where(p => p.PostID == PostID).FirstOrDefault();
            if (post != null)
            {
                db.Post.Remove(post);
                db.SaveChanges();

            }


        }

        public async Task<IList<Post>> ListarPosts()
        {
            return await db.Post.OrderByDescending(p => p.PostID).Take(20).ToListAsync();
        }

        public Post BuscarPosts(int postId)
        {
            return db.Post.Where(p => p.PostID == postId).FirstOrDefault();
        }

    }
}
