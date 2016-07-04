using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using System.Threading.Tasks;
using Android.Support.V7.App;

namespace Senac.CrossPlatform.Droid
{
    [Activity(Theme = "@style/MyTheme.Splash", MainLauncher = true, NoHistory = true)]
    public class ScreenActivity : AppCompatActivity
    {
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);


            var intent = new Intent(this, typeof(MainActivity));
            StartActivity(intent);
            Finish();
        }
        //protected override void OnResume()
        //{
        //    base.OnResume();

        //    Task startupWork = new Task(() => {
        //        Task.Delay(5000);  // Simulate a bit of startup work.
        //    });

        //    startupWork.ContinueWith(t => {

        //        StartActivity(new Intent(Application.Context, typeof(MainActivity)));
        //    }, TaskScheduler.FromCurrentSynchronizationContext());

        //    startupWork.Start();
        //}
    }
}