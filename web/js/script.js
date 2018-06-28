/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 $(document).ready(function(){
     $('.cep').mask('00000-000', {placeholder: "_____-___"});
    $('.phone').mask('(00) 0000-0000', {placeholder: "(__) ____-____"});
    $('.celular').mask('(00) 00000-0000', {placeholder: "(__) _____-____"});
    $('.cpf').mask('000.000.000-00', {reverse: true, placeholder: "___.___.___-__"});
    $('.cnpj').mask('00.000.000/0000-00', {reverse: true, placeholder: "__.___.___/____-__"});
    $('.money').mask('000.000.000.000.000,00', {reverse: true});
    $('.percent').mask('##0,00%', {reverse: true, placeholder: "%"});
    $('select').formSelect();
    $('.timepicker').timepicker({
        autoClose: true,
        closeOnSelect: true,
        container: 'main',
        format: 'dd/mm/yyyy',
        yearRange: [1920, 2100],
        i18n: {
            cancel: 'Cancelar',
            done: 'Salvar'
        },
        twelveHour: false
    });
    $('.datepicker').datepicker({
        autoClose: true,
        closeOnSelect: true,
        container: 'main',
        format: 'dd/mm/yyyy',
        yearRange: [1920, 2100],
        i18n: {
            cancel: 'Cancelar',
            months: [
                'Janeiro',
                'Fevereiro',
                'Março',
                'Abril',
                'Maio',
                'Junho',
                'Julho',
                'Agosto',
                'Setembro',
                'Outubro',
                'Novembro',
                'Dezembro'
            ],
            monthsShort: [
                'Jan',
                'Fev',
                'Mar',
                'Abr',
                'Mai',
                'Jun',
                'Jul',
                'Ago',
                'Set',
                'Out',
                'Nov',
                'Dez'
            ],
            weekdays:
                [
                    'Domingo',
                    'Segunda',
                    'Terça',
                    'Quarta',
                    'Quinta',
                    'Sexta',
                    'Sábado'
                ],
            weekdaysShort:
                [
                    'Dom',
                    'Seg',
                    'Ter',
                    'Qua',
                    'Qui',
                    'Sex',
                    'Sab'
                ],
            weekdaysAbbrev: ['S','T','Q','Q','S','S','D']
        }
    });
    $('span.error').each(function (elemento){
        if($(this).text().length > 0){
            $(this).attr('hidden', true);
            M.toast({html: $(this).text()}); 
        }
    });
        
});
