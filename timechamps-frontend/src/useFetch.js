import { isRef, unref, ref, watchEffect } from "vue";

export function useFetch(url, options = {}) {
  const data = ref(null);
  const isLoading = ref(false);
  const error = ref(null);

  function doFetch() {
    data.value = null;
    error.value = null;
    isLoading.value = true;

    fetch(unref(url), options)
      .then((res) => res.json())
      .then((json) => {
        data.value = json;
        isLoading.value = false;
      })
      .catch((err) => {
        error.value = err;
        isLoading.value = false;
      });
  }

  if (isRef(url)) {
    // setup reactive re-fetch if input URL is a ref
    watchEffect(doFetch);
  } else {
    // otherwise, just fetch once
    // and avoid the overhead of a watcher
    doFetch();
  }

  return { data, isLoading, error };
}
