using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Senac.CrossPlatform
{
    public interface IResizeImage
    {
        byte[] ResizeImage(byte[] imageData, float width, float height);
    }
}
