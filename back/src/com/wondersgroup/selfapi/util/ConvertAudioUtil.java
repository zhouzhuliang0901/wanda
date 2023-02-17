package com.wondersgroup.selfapi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.wondersgroup.selfapi.bean.WaveHeader;

public class ConvertAudioUtil {
	
	/**
	 * pcm文件转换成MP3文件
     * @param src    待转换文件路径
     * @param target 目标文件路径
     * @throws IOException 抛出异常
     */
    public static String convertPcmToMP3(String src, String target) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);
 
        //计算长度
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();
        
        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
        WaveHeader header = new WaveHeader();
        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 16000;//正常速度是8000，这里写成了16000，速度加快一倍
        header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;
 
        byte[] h = header.getHeader();
 
        assert h.length == 44; //WAV标准，头部应该是44字节
        //write header
        fos.write(h, 0, h.length);
        //write data stream
        fis = new FileInputStream(src);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("PCM Convert to MP3 OK!");
        return "ok";
    }
    
	/**
	 * MP3转换PCM文件方法
	 * @param mp3filepath
	 * @param pcmfilepath
	 * @throws Exception
	 */
	public static void mp3Convertpcm(String mp3filepath,String pcmfilepath) throws Exception{
		File mp3 = new File(mp3filepath);
		File pcm = new File(pcmfilepath);
		//原MP3文件转AudioInputStream
		AudioInputStream mp3audioStream = AudioSystem.getAudioInputStream(mp3);
		//将AudioInputStream MP3文件 转换为PCM AudioInputStream
		AudioInputStream pcmaudioStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, mp3audioStream);
		//准备转换的流输出到OutputStream
		OutputStream os = new FileOutputStream(pcm);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead=pcmaudioStream.read(buffer, 0, 8192))!=-1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		pcmaudioStream.close();
	}
}
