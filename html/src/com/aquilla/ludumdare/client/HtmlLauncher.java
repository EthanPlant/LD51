package com.aquilla.ludumdare.client;

import com.aquilla.ludumdare.util.Palette;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.aquilla.ludumdare.LudumDare;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.badlogic.gdx.graphics.Pixmap;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

public class HtmlLauncher extends GwtApplication {
        private GwtApplicationConfiguration cfg;
        @Override
        public GwtApplicationConfiguration getConfig () {
                cfg = new GwtApplicationConfiguration(true);
                Window.enableScrolling(false);
                Window.setMargin("0");

                LudumDare.mode = LudumDare.Mode.HTML;

                return cfg;
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new LudumDare();
        }

        @Override
        public Preloader.PreloaderCallback getPreloaderCallback() {
                final Canvas canvas = Canvas.createIfSupported();
                canvas.setWidth( "" + cfg.width  + "px");
                canvas.setHeight("" + cfg.height + "px");
                canvas.setCoordinateSpaceWidth(cfg.width);
                canvas.setCoordinateSpaceHeight(cfg.height);
                getRootPanel().add(canvas);

                final Context2d context = canvas.getContext2d();

                return new Preloader.PreloaderCallback() {

                        @Override
                        public void update (Preloader.PreloaderState state) {
                                float gap = 2;
                                float barWidth  = 80;
                                float barHeight = 12;
                                float x = (context.getCanvas().getWidth()  - barWidth)  / 2;
                                float y = (context.getCanvas().getHeight() - barHeight) / 2;

                                // Background bar.
                                String color = Pixmap.make(255, 255, 255, 1);
                                context.setFillStyle(color);
                                context.fillRect(x, y, barWidth, barHeight);

                                // Inbetween bar.
                                float barWidth2  = barWidth  - gap * 2;
                                float barHeight2 = barHeight - gap * 2;
                                float x2 = x + gap;
                                float y2 = y + gap;
                                String color2 = Pixmap.make((int) (Palette.INK.r * 255), (int) (Palette.INK.g * 255), (int) (Palette.INK.b * 255), 1);
                                context.setFillStyle(color2);
                                context.setStrokeStyle(color2);
                                context.fillRect(x2, y2, barWidth2, barHeight2);

                                // Loading bar.
                                float barWidth3  = (barWidth2  - (2 * gap)) * state.getProgress();
                                float barHeight3 = (barHeight2 - (2 * gap));
                                float x3 = x2 + gap;
                                float y3 = y2 + gap;
                                String barColour = Pixmap.make(255, 255, 255, 1);
                                context.setFillStyle(barColour);
                                context.setStrokeStyle(barColour);
                                context.fillRect(x3, y3, barWidth3, barHeight3);
                        }

                        @Override
                        public void error (String file) {
                                System.out.println("error: " + file);
                        }

                };

        }

        class ResizeListener implements ResizeHandler {

                @Override
                public void onResize(ResizeEvent event) {
                        int width = event.getWidth();
                        int height = event.getHeight();

                        getApplicationListener().resize(width, height);
                        Gdx.graphics.setWindowedMode(width, height);
                        Gdx.gl.glViewport(0, 0, width, height);

                        Window.scrollTo((cfg.width - width) / 2, (cfg.height - height) / 2);
                }

        }
}