using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Business.Interface
{
    public interface IPostBusiness
    {
        void InserirPost(Post post);
        void AtualizarPost(Post post);
        void ExcluirPost(int PostID);
        Task<IList<Post>> ListarPosts();
    }
}
