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
using Senac.CrossPlatform.Componentes;
using Xamarin.Forms;
using Senac.CrossPlatform.Droid.Componentes;
using Xamarin.Forms.Platform.Android;
using Android.Graphics.Drawables;
using System.ComponentModel;
using Android.Graphics.Drawables.Shapes;
using Android.Graphics;

[assembly: ExportRenderer(typeof(SenacEntry), typeof(SenacEntryRenderer))]
namespace Senac.CrossPlatform.Droid.Componentes
{
    public class SenacEntryRenderer : EntryRenderer
    {
        private GradientDrawable _normal, _pressed;

        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);

            if (Control != null)
            {
                var entry = e.NewElement;

                //// Create a drawable for the button's normal state
                //_normal = new Android.Graphics.Drawables.GradientDrawable();

                //if (entry.BackgroundColor.R == -1.0 && entry.BackgroundColor.G == -1.0 && entry.BackgroundColor.B == -1.0)
                //    _normal.SetColor(Android.Graphics.Color.ParseColor("#ff2c2e2f"));
                //else
                //    _normal.SetColor(entry.BackgroundColor.ToAndroid());

                ////_normal.SetStroke((int)entry.BorderWidth, entry.BorderColor.ToAndroid());
                ////_normal.SetCornerRadius(entry.BorderRadius);

                //// Create a drawable for the button's pressed state
                //_pressed = new Android.Graphics.Drawables.GradientDrawable();
                //var highlight = Context.ObtainStyledAttributes(new int[] { Android.Resource.Attribute.ColorActivatedHighlight }).GetColor(0, Android.Graphics.Color.Gray);
                //_pressed.SetColor(highlight);
                ////_pressed.SetStroke((int)entry.BorderWidth, entry.BorderColor.ToAndroid());
                ////_pressed.SetCornerRadius(entry.BorderRadius);

                //// Add the drawables to a state list and assign the state list to the button
                //var sld = new StateListDrawable();
                //sld.AddState(new int[] { Android.Resource.Attribute.StatePressed }, _pressed);
                //sld.AddState(new int[] { }, _normal);
                //Control.SetBackgroundDrawable(sld);



                var shape = new ShapeDrawable(new RectShape());
                shape.Paint.Color = Android.Graphics.Color.ParseColor("#c2c2d6");
                shape.Paint.StrokeWidth = 2;
                shape.Paint.SetStyle(Paint.Style.Stroke);

#pragma warning disable CS0618 // Type or member is obsolete
                Control.SetBackgroundDrawable(shape);
#pragma warning restore CS0618 // Type or member is obsolete
                
            }
        }

        protected override void OnElementPropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            base.OnElementPropertyChanged(sender, e);
            var entry = (Xamarin.Forms.Entry)sender;

            if (_normal != null && _pressed != null)
            {
                if (e.PropertyName == "BorderRadius")
                {
                    //_normal.SetCornerRadius(entry.BorderRadius);
                    //_pressed.SetCornerRadius(entry.BorderRadius);
                }
                if (e.PropertyName == "BorderWidth" || e.PropertyName == "BorderColor")
                {
                    //_normal.SetStroke((int)entry.BorderWidth, entry.BorderColor.ToAndroid());
                    //_pressed.SetStroke((int)entry.BorderWidth, entry.BorderColor.ToAndroid());
                }
            }
        }

    }
}