using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform.ViewModel
{
    public class TokenResponse
    {
        public string access_token { get; set; }
        public string refresh_token { get; set; }
        public HttpStatusCode StatusCode { get; set; }
    }
}
