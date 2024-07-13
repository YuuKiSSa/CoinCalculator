# 使用官方 Node.js 作为基础镜像，用于构建和运行前端应用
FROM node:14-alpine

# 设置工作目录
WORKDIR /app

# 复制 package.json 和 package-lock.json 并安装依赖
COPY package*.json ./
RUN npm install

# 复制所有文件到工作目录
COPY . .

# 构建生产环境代码
RUN npm run build

# 设置 Nginx 服务器作为运行时环境
FROM nginx:alpine

# 复制构建好的静态文件到 Nginx 的默认静态文件目录
COPY --from=0 /app/build /usr/share/nginx/html

# 暴露容器的80端口
EXPOSE 80

# 设置 Nginx 启动命令
CMD ["nginx", "-g", "daemon off;"]
