//author Isabelle Bustamante



import TokenStorageService from "./token-storage.service";

export default function authHeader() {
  const accessToken = TokenStorageService.getToken();

  if (accessToken) {
    return { Authorization: "Bearer " + accessToken };
  } else {
    return {};
  }
}
