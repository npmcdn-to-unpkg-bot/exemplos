using Newtonsoft.Json;
using Senac.CrossPlatform.Data;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform.Service
{
    public class UsuarioService
    {
        private HttpClient _client;
        public UsuarioService()
        {
            _client = new HttpClient
            {
                //TODO: alterar endereço de acesso ao webservice
                BaseAddress = new Uri(Configuration.serviceUrl)
            };
        }

        public void InserirUsuario(Usuario usuario)
        {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("Nome", usuario.Nome),
                new KeyValuePair<string,string>("Cpf", usuario.Cpf),
                new KeyValuePair<string,string>("Senha", usuario.Senha),
            });

            _client.PostAsync("/Usuario/InserirUsuario", content);
        }


        public async Task<bool> AtualizarUsuario(Usuario usuario)
        {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("UsuarioID", usuario.UsuarioID.ToString()),
                new KeyValuePair<string,string>("Nome", usuario.Nome),
                new KeyValuePair<string,string>("Cpf", usuario.Cpf),
                new KeyValuePair<string,string>("Senha", usuario.Senha),
            });

            var response  = await _client.PutAsync("api/Usuario", content);

            if(response.IsSuccessStatusCode)
            {
                return true;
            }
            return false;
        }

        /*
        public async Task<bool> ProcurarUsuario(string Cpf) {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("Cpf", Cpf)
            });
            var response = _client.PostAsync("/Usuario/ProcurarUsuario", content).Result;
            var content2 = await response.Content.ReadAsStringAsync();
            var resultado = JsonConvert.DeserializeObject<bool>(content2);
            return resultado;
    }
    */

        //TODO: verificar como retornar true ou false a partir da requisição async
        public async Task<Usuario> ProcurarUsuario(string Cpf)
        {
            var result = await _client.GetAsync("api/Usuario?cpf=" + Helper.removeMascara(Cpf));
            if (result.IsSuccessStatusCode)
            {
                var content2 = await result.Content.ReadAsStringAsync();
                var resposta = JsonConvert.DeserializeObject<Usuario>(content2);
                return resposta;
            }

            return null;
        }

        public async Task<TokenResponse> AutenticarUsuario(string cpf, string senha)
        {
            var erro = "";
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("username", cpf),
                new KeyValuePair<string,string>("password", senha),
                new KeyValuePair<string,string>("client_id", "senacapp"),
                new KeyValuePair<string,string>("grant_type", "password")
            });
            var resposta = (await _client.PostAsync("Token", content));
            

            var content2 = await resposta.Content.ReadAsStringAsync();
            var resultado = JsonConvert.DeserializeObject<TokenResponse>(content2);
            resultado.StatusCode = resposta.StatusCode;
            return resultado;
        }



        public string AtualizarCredenciais(Token token)
        {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("grant_type", "refresh_token"),
                new KeyValuePair<string,string>("refresh_token", token.RefreshToken)
            });

            return _client.PostAsync("/Token", content).Result.ToString();
        }
    }
}
