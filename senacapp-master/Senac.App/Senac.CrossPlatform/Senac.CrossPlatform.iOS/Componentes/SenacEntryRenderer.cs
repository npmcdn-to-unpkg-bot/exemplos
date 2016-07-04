using System;
using Xamarin.Forms;
using Senac.CrossPlatform.Componentes;
using Senac.CrossPlatform.iOS;
using Xamarin.Forms.Platform.iOS;



[assembly: ExportRenderer(typeof(SenacEntry), typeof(SenacEntryRenderer))]
namespace Senac.CrossPlatform.iOS
{
	public class SenacEntryRenderer : EntryRenderer
	{
		public SenacEntryRenderer ()
		{
		}



		protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
		{
			base.OnElementChanged(e);

			if (Control != null) {

				//Aqui vao os elementos nativos da plataforma

			}


		}



	}
}

