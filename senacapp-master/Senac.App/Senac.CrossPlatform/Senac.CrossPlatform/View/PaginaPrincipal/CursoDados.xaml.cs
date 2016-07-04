using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

namespace Senac.CrossPlatform.View.PaginaPrincipal
{
    public partial class CursoDados : TabbedPage
    {
        public CursoDados()
        {
            NavigationPage.SetHasNavigationBar(this, false);
            InitializeComponent();
        }
        public CursoDados(int cursoId) : this()
        {

            var navigatePageFeed = new NavigationPage(new Feed());
            navigatePageFeed.Title = "Feed";

            var navigatePageFrequencia = new NavigationPage(new Feed());
            navigatePageFeed.Title = "Frequencia";

            var navigatePageCertificado = new NavigationPage(new Feed());
            navigatePageFeed.Title = "Certificado";

            Children.Add(navigatePageFeed);
            Children.Add(navigatePageFrequencia);
            Children.Add(navigatePageCertificado);
        }

    }
}
