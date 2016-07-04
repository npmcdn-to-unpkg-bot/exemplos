using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.Provider;
using Xamarin.Forms;
using Geolocator.Plugin;
using Senac.CrossPlatform.Droid;

[assembly: Dependency(typeof(AndroidDevice))]
namespace Senac.CrossPlatform.Droid
{
    class AndroidDevice : IDevice
    {
        public string GetIndentifier()
        {
            return Settings.Secure.GetString(Forms.Context.ContentResolver, Settings.Secure.AndroidId);
        }

        public async Task<string> GetLocation()
        {
            var locator = CrossGeolocator.Current;

            locator.DesiredAccuracy = 100; //100 is new default

            var position = await locator.GetPositionAsync(timeoutMilliseconds: 10000);

            Console.WriteLine("Position Status: {0}", position.Timestamp);

            Console.WriteLine("Position Latitude: {0}", position.Latitude);

            Console.WriteLine("Position Longitude: {0}", position.Longitude);
            return position.Latitude + ", " + position.Longitude;
        }
    }
}