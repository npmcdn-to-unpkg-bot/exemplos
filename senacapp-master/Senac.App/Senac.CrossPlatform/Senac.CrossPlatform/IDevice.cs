using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform
{
    public interface IDevice
    {
        Task<string> GetLocation();
        string GetIndentifier();
    }
}
