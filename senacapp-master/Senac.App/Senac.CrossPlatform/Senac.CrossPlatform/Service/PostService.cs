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
    public class PostService : BaseService, IDisposable
    {
        private HttpClient client;
        public PostService()
        {
            client = new HttpClient
            {
                BaseAddress = new Uri(Configuration.serviceUrl)
            };
        }

       
        public void InserirPost(Post post)
        {
            var jsonString = JsonConvert.SerializeObject(post);
            var content = new StringContent(jsonString, Encoding.UTF8, "application/json");

            client.PostAsync("api/Post", content);
        }

        public async Task<List<Post>> ListarPosts()
        {
            var response = client.GetAsync("api/Post").Result;
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var resultado = JsonConvert.DeserializeObject<List<Post>>(content);
                return resultado;
            }
            return null;
        }

        public async Task<bool> ExcluirPost(int postId)
        {
            var response = client.DeleteAsync("/api/Post?PostID=" + postId.ToString()).Result;

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var resultado = Convert.ToBoolean(content);
                return resultado;
            }
            return false;
        }

        public async Task<Post> BuscarPost(int postId)
        {
            var response = client.GetAsync("api/Post/" + postId.ToString()).Result;

            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var resultado = JsonConvert.DeserializeObject<Post>(content);
                return resultado;
            }
            return null;
        }

        public async Task<bool> EditarPost(Post post)
        {
            var content = new FormUrlEncodedContent(new[] {
                new KeyValuePair<string,string>("Imagem",post.Imagem),
                new KeyValuePair<string,string>("Titulo",post.Titulo),
                new KeyValuePair<string,string>("Texto", post.Texto),
                new KeyValuePair<string,string>("Cidade",post.Cidade),
                new KeyValuePair<string,string>("Unidade",post.Unidade),
                new KeyValuePair<string,string>("Area",post.Area)
            });


            var response = client.PutAsync("/Post/EditarPost", content).Result;

            if (response.IsSuccessStatusCode)
            {
                var resultContent = await response.Content.ReadAsStringAsync();
                var resultado = Convert.ToBoolean(resultContent);
                return resultado;
            }
            return false;

        }

        public void Dispose()
        {
            GC.SuppressFinalize(this);
        }
    }
}
