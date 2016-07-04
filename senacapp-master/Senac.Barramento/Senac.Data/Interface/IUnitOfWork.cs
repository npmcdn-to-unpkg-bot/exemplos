using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Interface
{
    public interface IUnitOfWork
    {
        IPostRepository PostRespository{get;}
        IComentarioRepository ComentarioRepository { get; }
        IAuthRepository AuthRepository { get; }
        //ISenacDB SenacDB { get; }
        IUsuarioRepository UsuarioRepository { get;}

        void commit();   
    }
}
