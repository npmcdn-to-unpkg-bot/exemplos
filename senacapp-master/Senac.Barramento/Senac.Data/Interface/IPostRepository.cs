using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Interface
{
    public interface IPostRepository
    {
        void InserirPost(Post post);
        Task<IList<Post>> ListarPosts();
        void AtualizarPost(Post post);
        void ExcluirPost(int PostID);
    }
}
