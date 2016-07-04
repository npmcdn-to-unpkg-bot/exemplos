using Foundation;
using Geolocator.Plugin;
using Senac.CrossPlatform.iOS;
using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

[assembly: Xamarin.Forms.Dependency(typeof(IOSDevice))]
namespace Senac.CrossPlatform.iOS
{
    public class IOSDevice : IDevice
    {
        [DllImport("/System/Library/Frameworks/IOKit.framework/IOKit")]
        private static extern uint IOServiceGetMatchingService(uint masterPort, IntPtr matching);

        [DllImport("/System/Library/Frameworks/IOKit.framework/IOKit")]
        private static extern IntPtr IOServiceMatching(string s);

        [DllImport("/System/Library/Frameworks/IOKit.framework/IOKit")]
        private static extern IntPtr IORegistryEntryCreateCFProperty(uint entry, IntPtr key, IntPtr allocator, uint options);

        [DllImport("/System/Library/Frameworks/IOKit.framework/IOKit")]
        private static extern int IOObjectRelease(uint o);

        public string GetIndentifier()
        {
            string serial = string.Empty;
            uint platformExpert = IOServiceGetMatchingService(0, IOServiceMatching("IOPlatformExpertDevice"));
            if (platformExpert != 0)
            {
                NSString key = (NSString)"IOPlatformSerialNumber";
                IntPtr serialNumber = IORegistryEntryCreateCFProperty(platformExpert, key.Handle, IntPtr.Zero, 0);
                if (serialNumber != IntPtr.Zero)
                {
                    serial = NSString.FromHandle(serialNumber);
                }

                IOObjectRelease(platformExpert);
            }

            return serial;
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
