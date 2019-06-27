package hello.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
public class ImageServiceImpl {


    public void imageHandler(String imageName) throws Exception {
        int x = 0;

        x = getScreenPixel(100, 345);
        System.out.println(x + " - ");
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(imageName);
        System.out.println(courseFile);
        //getImagePixel("E:\\test.png");
        getImagePixel(courseFile + "/upload-dir/" + imageName);
    }


    /**
     * 读取一张图片的RGB值
     *
     * @throws Exception
     */
    public void getImagePixel(String image) throws Exception {
        int[] rgb = new int[3];
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        System.out.println("width=" + width + ",height=" + height + ".");
        System.out.println("minx=" + minx + ",miniy=" + miny + ".");

        int a = 0;
        int b = 0;
        //第一区域


        for (int h = 4; h < 127; h = h + 21) {
            for (int w = 12; w < 600; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");
                if (w % 21 == 12) {
                    System.out.println("我是一个单元");
                    if (rgb[0] < 175 && rgb[2] > 175) {
                        System.out.println("蓝色");
                        a = a + 1;
                        System.out.println("蓝色实时个数" + a);
                    } else if (rgb[0] > 175 && rgb[2] < 175) {
                        System.out.println("红色");
                        b = b + 1;
                        System.out.println("红色实时个数" + a);
                    }
                }
            }
        }

        /////////////////////////////////////////////////////////////////////////////////
        //第二区域测试 四个for循环

        for (int h = 128; h < 186; h = h + 21) {
            for (int w = 6; w < 420; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元");
                if (rgb[0] < 200 && rgb[2] > 175) {
                    System.out.println("蓝色");
                    a++;
                    System.out.println("蓝色实时个数" + a);
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    System.out.println("红色");
                    b++;
                    System.out.println("红色实时个数" + a);
                }

            }
        }

        for (int h = 128; h < 186; h = h + 21) {
            for (int w = 16; w < 420; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元");
                if (rgb[0] < 200 && rgb[2] > 175) {
                    System.out.println("蓝色");
                    a++;
                    System.out.println("蓝色实时个数" + a);
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    System.out.println("红色");
                    b++;
                    System.out.println("红色实时个数" + a);
                }

            }
        }

        for (int h = 138; h < 186; h = h + 21) {
            for (int w = 6; w < 420; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元");
                if (rgb[0] < 200 && rgb[2] > 175) {
                    System.out.println("蓝色");
                    a++;
                    System.out.println("蓝色实时个数" + a);
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    System.out.println("红色");
                    b++;
                    System.out.println("红色实时个数" + a);
                }

            }
        }

        for (int h = 138; h < 186; h = h + 21) {
            for (int w = 16; w < 420; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元139");
                if (rgb[0] < 200 && rgb[2] > 175) {
                    System.out.println("蓝色");
                    a++;
                    System.out.println("蓝色实时个数" + a);
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    System.out.println("红色");
                    b++;
                    System.out.println("红色实时个数" + a);
                }

            }
        }


        ////////////////////////////////////////////////////////////////////////////////////

        // 第三区域 两次width 位置起点不同 间距一样的for循环 为了解决间隔是小数的问题
        for (int h = 194; h < 255; h = h + 10) {
            for (int w = 6; w < 420; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元");
                if (rgb[0] < 175 && rgb[2] > 175) {
                    System.out.println("蓝色");
                    a++;
                    System.out.println("蓝色实时个数" + a);
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    System.out.println("红色");
                    b++;
                    System.out.println("红色实时个数" + a);
                }

            }
        }
        for (int h = 194; h < 255; h = h + 10) {
            for (int w = 16; w < 420; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元");
                if (rgb[0] < 175 && rgb[2] > 175) {
                    System.out.println("蓝色");
                    a++;
                    System.out.println("蓝色实时个数" + a);
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    System.out.println("红色");
                    b++;
                    System.out.println("红色实时个数" + a);
                }

            }
        }


        /////////////////////////////////////////////////////////////////////////////////////
        //第四区域

        for (int h = 194; h < 255; h = h + 21) {
            for (int w = 425; w < 570; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                if (rgb[0] < 200 && rgb[2] > 175) {
                    a++;
                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    b++;
                }

            }
        }

        for (int h = 194; h < 255; h = h + 21) {
            for (int w = 435; w < 570; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("h=" + h + ",w=" + w + ":(" + rgb[0] + ","
                        + rgb[1] + "," + rgb[2] + ")");

                System.out.println("我是一个单元");
                if (rgb[0] < 200 && rgb[2] > 175) {
                    a++;
                } else if (rgb[0] > 175 && rgb[2] < 175) {

                    b++;
                }

            }
        }

        for (int h = 204; h < 255; h = h + 21) {
            for (int w = 425; w < 570; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                System.out.println("我是一个单元");
                if (rgb[0] < 200 && rgb[2] > 175) {
                    a++;

                } else if (rgb[0] > 175 && rgb[2] < 175) {
                    b++;
                }

            }
        }

        for (int h = 204; h < 255; h = h + 21) {
            for (int w = 435; w < 570; w = w + 21) {
                int pixel = bi.getRGB(w, h); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                if (rgb[0] < 200 && rgb[2] > 175) {
                    a++;

                } else if (rgb[0] > 175 && rgb[2] < 175) {

                    b++;

                }

            }
        }


        System.out.println("蓝色的个数" + a);
        System.out.println("红色的个数" + b);
    }

    public void forTest() {

    }

    /**
     * 返回屏幕色彩值
     *
     * @param x
     * @param y
     * @return
     * @throws AWTException
     */
    public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
        rb = new Robot();
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        BufferedImage bi = rb.createScreenCapture(rec);
        int pixelColor = bi.getRGB(x, y);

        return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
    }

}
