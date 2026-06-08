# CI/CD Selenium TestNG - TLU Login

Project kiểm thử tự động login trang sinh viên TLU bằng Java 21, Selenium, TestNG, Maven và GitHub Actions.

## Kịch bản test

1. Đăng nhập đúng tài khoản và mật khẩu.
2. Đăng nhập đúng tài khoản nhưng sai mật khẩu.

## Bảo mật tài khoản

Không lưu mật khẩu thật trong source code. Khi chạy GitHub Actions, hãy tạo Repository Secrets:

- `TLU_USERNAME`
- `TLU_PASSWORD`

Vào: `Repository -> Settings -> Secrets and variables -> Actions -> New repository secret`.

## Chạy local bằng PowerShell

```powershell
$env:TLU_USERNAME="your_username"
$env:TLU_PASSWORD="your_password"
mvn clean test
```

Nếu Maven chưa vào PATH, chạy tạm bằng đường dẫn đầy đủ:

```powershell
C:\Tester\apache-maven-3.9.16\bin\mvn.cmd clean test
```

## GitHub Actions

Workflow nằm ở `.github/workflows/selenium-tests.yml` và tự chạy khi push code lên branch `main`.
Sau khi chạy, xem report tại: `Actions -> workflow run -> Artifacts`.
