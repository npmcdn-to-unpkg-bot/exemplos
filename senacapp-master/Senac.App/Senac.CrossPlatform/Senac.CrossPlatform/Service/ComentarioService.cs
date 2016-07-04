using Newtonsoft.Json;
using Senac.CrossPlatform.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform.Service
{
    public class ComentarioService : BaseService
    {
        private HttpClient _client;

        public ComentarioService()
        {
            _client = new HttpClient
            {
                BaseAddress = new Uri(Configuration.serviceUrl)
            };
        }

        public bool InserirComentario(Comentario comentario)
        {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("Texto",comentario.Texto),
                new KeyValuePair<string,string>("Usuario",Convert.ToString(comentario.Usuario)),
            });

            var response = _client.PostAsync("/api/Comentario", content).Result;

            if(response.IsSuccessStatusCode)
            {
                return true;
            }
            return false;
        }

        public async Task<List<Comentario>> ListarComentarios()
        {
            var response = _client.GetAsync("api/Comentario").Result;
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var resultado = JsonConvert.DeserializeObject<List<Comentario>>(content);
                return resultado;
            }
            return null;
        }

        public async Task<bool> DeletarComentario(int comentarioId)
        {
            var response = _client.DeleteAsync("api/Comentario?comentarioId=" + comentarioId.ToString()).Result;

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var resultado = Convert.ToBoolean(content);
                return resultado;
            }
            return false;
        }



        public async Task<Post> BuscarComentario(int comentarioId)
        {
            var response = _client.GetAsync("api/Comentario/" + comentarioId.ToString()).Result;

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var resultado = JsonConvert.DeserializeObject<Post>(content);
                return resultado;
            }
            return null;
        }



        public async Task<bool> EditarComentario(Comentario comentario)
        {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("Texto",comentario.Texto),
            });


            var response = _client.PutAsync("/Comentario/EditarComentario", content).Result;

            if (response.IsSuccessStatusCode)
            {
                var resultContent = await response.Content.ReadAsStringAsync();
                var resultado = Convert.ToBoolean(resultContent);
                return resultado;
            }
            return false;

        }

    }
}
