/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jianyun.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * <pre>
 * 名称: DownloadUtil
 * 描述: DownloadUtil
 * </pre>
 * @author jianyun.org.cn
 * @since 1.0.0
 */
public final class DownloadUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(DownloadUtil.class);

    private DownloadUtil() {
        super();
    }

    /**
     * 下载文件.
     * @param response HttpServletResponse
     * @param filePath 文件路径
     * @throws Exception Exception
     */
    public static void downloadFile(final HttpServletResponse response, final String filePath) throws Exception {
        // String filePath = request.getSession().getServletContext().getRealPath("/static/soft/shiftModel.xlsx");
        // filePath是指欲下载的文件的路径。
        File file = new File(filePath);
        if (!file.exists()) {
            LOGGER.error("文件不存在或异常, path: {}", filePath);
            throw new FileNotFoundException("文件不存在或异常");
        }
        try {
            // 取得文件名
            String fileName = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            //下载
            download(response, fileName, buffer);
        } catch (final Throwable e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("系统错误");
        }
    }

    /**
     * 下载bytes.
     * @param response HttpServletResponse
     * @param fileName 文件名
     * @param buffer byte[]
     */
    public static void download(final HttpServletResponse response, final String fileName, final byte[] buffer) {
        try {
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            OutputStream out = response.getOutputStream();
            out.write(buffer);
            out.flush();
            out.close();
        } catch (final Throwable e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("系统错误");
        }
    }

}
