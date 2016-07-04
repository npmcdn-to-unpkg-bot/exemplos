using Senac.Business.Interface;
using Senac.Data.Model;
using Senac.Data.Repository;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Senac.Business.Business
{
    public class AuthBusiness : IAuthBusiness, IDisposable
    {
        private AuthRepository _authRepository;
        public AuthBusiness()
        {
            _authRepository = new AuthRepository();
        }

        public Client FindClient(string clientId)
        {
            return _authRepository.FindClient(clientId);
        }

        public async Task<bool> AddRefreshToken(RefreshToken token)
        {
            return await _authRepository.AddRefreshToken(token);
        }

        public async Task<bool> RemoveRefreshToken(string refreshTokenId)
        {
            return await _authRepository.RemoveRefreshToken(refreshTokenId);
        }

        public async Task<bool> RemoveRefreshToken(RefreshToken refreshToken)
        {
            return await _authRepository.RemoveRefreshToken(refreshToken);
        }

        public async Task<RefreshToken> FindRefreshToken(string refreshTokenId)
        {
            return await _authRepository.FindRefreshToken(refreshTokenId);
        }

        public List<RefreshToken> GetAllRefreshTokens()
        {
            return _authRepository.GetAllRefreshTokens();
        }

        public void Dispose()
        {
            //GC.SuppressFinalize(this);
        }
    }
}