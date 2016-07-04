using Senac.Data.Interface;
using Senac.Data.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data
{
    public class UnitOfWork : IUnitOfWork
    {
        private ISenacDB _senacDb;
        private PostRepository _postRepository;
        private ComentarioRepository _comentarioRepository;
        private AuthRepository _authRepository;
        private UsuarioRepository _usuarioRepository;

        public UnitOfWork(ISenacDB senacDb)
        {
            _senacDb = senacDb;
        }


        public IPostRepository PostRespository
        {
            get
            {
                if (_postRepository == null)
                {
                    _postRepository = new PostRepository();
                }
                return _postRepository;
            }
        }


        public IComentarioRepository ComentarioRepository
        {
            get
            {
                if (_comentarioRepository == null)
                {
                    _comentarioRepository = new ComentarioRepository();
                }
                return _comentarioRepository;
            }
        }

        public IAuthRepository AuthRepository
        {
            get
            {
                if (_authRepository == null)
                {
                    _authRepository = new  AuthRepository();
                }
                return _authRepository;
            }
        }

        public IUsuarioRepository UsuarioRepository
        {
            get
            {
                if (_usuarioRepository == null)
                {
                    _usuarioRepository = new  UsuarioRepository();
                }
                return _usuarioRepository;
            }

        }



        public void commit()
        {
            _senacDb.SaveChanges();
        }
    }
}
