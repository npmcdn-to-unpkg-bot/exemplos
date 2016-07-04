using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Senac.CrossPlatform.ViewModel
{
    public class Post
    {
        public int PostID { get; set; }
        public Usuario Usuario { get; set; }

        public String Imagem { get; set; }
        public String Titulo { get; set; }
        public String Texto { get; set; }
        public String Cidade { get; set; }
        public String Unidade { get; set; }
        public String Area { get; set; }
        public List<Comentario> Comentarios { get; set; }

        [JsonIgnore]
        public ImageSource ImagemStream
        {
            get
            {
                if (!string.IsNullOrEmpty(Imagem))
                {
                    var stream = new MemoryStream(Convert.FromBase64String(Imagem));
                    return ImageSource.FromStream(() => { return stream; });
                }
                return null;
            }
        }

        [JsonIgnore]
        public int AlturaImagem
        {
            get {
                if (!string.IsNullOrEmpty(Imagem))
                {
                    return 350;
                }
                return 0;
            }
        }
    }
}
