using Senac.Data.Interface;
using Senac.Data.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.Data.Repository
{
    public class AuthRepository : IAuthRepository
    {
        private SenacDB _db;
        public AuthRepository()
        {
            _db = new SenacDB();
        }
        public Client FindClient(string clientId)
        {
            var client = _db.Clients.Where(p => p.ClientName == clientId).FirstOrDefault();

            return client;
        }

        public async Task<bool> AddRefreshToken(RefreshToken token)
        {

            var existingToken = _db.RefreshTokens.Where(r => r.Subject == token.Subject && r.ClientId == token.ClientId).FirstOrDefault();

            if (existingToken != null)
            {
                var result = await RemoveRefreshToken(existingToken);
            }

            _db.RefreshTokens.Add(token);

            return await _db.SaveChangesAsync() > 0;
        }

        public async Task<bool> RemoveRefreshToken(string refreshTokenId)
        {
            var refreshToken = await _db.RefreshTokens.FindAsync(refreshTokenId);

            if (refreshToken != null)
            {
                _db.RefreshTokens.Remove(refreshToken);
                return await _db.SaveChangesAsync() > 0;
            }

            return false;
        }

        public async Task<bool> RemoveRefreshToken(RefreshToken refreshToken)
        {
            _db.RefreshTokens.Remove(refreshToken);
            return await _db.SaveChangesAsync() > 0;
        }

        public async Task<RefreshToken> FindRefreshToken(string refreshTokenId)
        {
            var refreshToken = await _db.RefreshTokens.FindAsync(refreshTokenId);

            return refreshToken;
        }

        public List<RefreshToken> GetAllRefreshTokens()
        {
            return _db.RefreshTokens.ToList();
        }

        public bool InserirUsuario(Usuario usuario)
        {
            throw new NotImplementedException();
        }

        public bool AtualizarUsuario(Usuario usuario)
        {
            throw new NotImplementedException();
        }

        public bool RemoverUsuario(int usuarioId)
        {
            throw new NotImplementedException();
        }
    }
}
