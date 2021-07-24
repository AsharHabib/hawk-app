function checkPasswordMatch(form){
    let password1 = form.inputPassword.value
    let password2 = form.inputPasswordRetype.value
    if (password1.length < 5){
        let position = document.querySelector('.qualify')
        position.classList.add('unmet')
    }
    if (password1 != password2 && password2.length > 0){
        let alert = document.createElement('li').setAttribute('class', 'unmet')
        alert.innerText = "Please make sure your passwords match"
        let position = document.querySelector('.retype')
        position.appendChild(alert)
    }
}