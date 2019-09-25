// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Wallet.proto

package alv.wallet;

public final class Wallet {
  private Wallet() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alv_wallet_DepositRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alv_wallet_DepositRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alv_wallet_WithdrawRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alv_wallet_WithdrawRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alv_wallet_BalanceRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alv_wallet_BalanceRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alv_wallet_BalanceResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alv_wallet_BalanceResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alv_wallet_BalanceResponse_CurrencyAmount_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alv_wallet_BalanceResponse_CurrencyAmount_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_alv_wallet_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_alv_wallet_Empty_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Wallet.proto\022\nalv.wallet\"]\n\016DepositReq" +
      "uest\022\017\n\007user_id\030\001 \001(\005\022\016\n\006amount\030\002 \001(\001\022*\n" +
      "\010currency\030\003 \001(\0162\030.alv.wallet.CurrencyTyp" +
      "e\"^\n\017WithdrawRequest\022\017\n\007user_id\030\001 \001(\005\022\016\n" +
      "\006amount\030\002 \001(\001\022*\n\010currency\030\003 \001(\0162\030.alv.wa" +
      "llet.CurrencyType\"!\n\016BalanceRequest\022\017\n\007u" +
      "ser_id\030\001 \001(\005\"\234\001\n\017BalanceResponse\022;\n\007bala" +
      "nce\030\001 \003(\0132*.alv.wallet.BalanceResponse.C" +
      "urrencyAmount\032L\n\016CurrencyAmount\022*\n\010curre" +
      "ncy\030\001 \001(\0162\030.alv.wallet.CurrencyType\022\016\n\006a" +
      "mount\030\002 \001(\001\"\007\n\005Empty*)\n\014CurrencyType\022\007\n\003" +
      "EUR\020\000\022\007\n\003USD\020\001\022\007\n\003GPB\020\0022\311\001\n\rWalletServic" +
      "e\0228\n\007deposit\022\032.alv.wallet.DepositRequest" +
      "\032\021.alv.wallet.Empty\022:\n\010withdraw\022\033.alv.wa" +
      "llet.WithdrawRequest\032\021.alv.wallet.Empty\022" +
      "B\n\007balance\022\032.alv.wallet.BalanceRequest\032\033" +
      ".alv.wallet.BalanceResponseB\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_alv_wallet_DepositRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_alv_wallet_DepositRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alv_wallet_DepositRequest_descriptor,
        new java.lang.String[] { "UserId", "Amount", "Currency", });
    internal_static_alv_wallet_WithdrawRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_alv_wallet_WithdrawRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alv_wallet_WithdrawRequest_descriptor,
        new java.lang.String[] { "UserId", "Amount", "Currency", });
    internal_static_alv_wallet_BalanceRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_alv_wallet_BalanceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alv_wallet_BalanceRequest_descriptor,
        new java.lang.String[] { "UserId", });
    internal_static_alv_wallet_BalanceResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_alv_wallet_BalanceResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alv_wallet_BalanceResponse_descriptor,
        new java.lang.String[] { "Balance", });
    internal_static_alv_wallet_BalanceResponse_CurrencyAmount_descriptor =
      internal_static_alv_wallet_BalanceResponse_descriptor.getNestedTypes().get(0);
    internal_static_alv_wallet_BalanceResponse_CurrencyAmount_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alv_wallet_BalanceResponse_CurrencyAmount_descriptor,
        new java.lang.String[] { "Currency", "Amount", });
    internal_static_alv_wallet_Empty_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_alv_wallet_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_alv_wallet_Empty_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
