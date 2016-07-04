using System;
using Xamarin.Forms;
using Senac.CrossPlatform.Componentes;
using Senac.CrossPlatform.iOS;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(SenacImage), typeof(SenacImageRenderer))]
namespace Senac.CrossPlatform.iOS
{
	public class SenacImageRenderer:ImageRenderer
	{
		public SenacImageRenderer ()
		{
		}
	}
}

